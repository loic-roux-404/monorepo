import { Injectable } from '@nestjs/common';
import { AdapterPayload } from 'oidc-provider';
import { BaseGrant, Grantable, DeviceCode, Session } from '../oidc/grant.model';

@Injectable()
export class AdapterService {
  private model: typeof BaseGrant | typeof Grantable | typeof DeviceCode | typeof Session;

  async upsert(id: string, data: AdapterPayload, expiresIn?: number) {
    await this.model.upsert({
      id,
      data,
      ...(this.model instanceof Grantable
          ? { grantId: data.grantId }
          : undefined
          ),
      ...(this.model instanceof DeviceCode ? { userCode: data.userCode } : undefined),
      ...(this.model instanceof Session ? { uid: data.uid } : undefined),
      ...(expiresIn ? { expiresAt: new Date(Date.now() + (expiresIn * 1000)) } : undefined),
    });
  }

  async find(id: string) {
    const found = await this.model.findByPk(id);
    if (!found) return undefined;
    return {
      ...found.data,
      ...{ consumed: true },
    };
  }

  async findByUserCode(userCode: string) {
    if (!(this.model instanceof DeviceCode)) throw new Error('Not device code type used')
    const found = await this.model.findOne({ where: { userCode } } as any);
    if (!found) return undefined;
    return {
      ...found.data,
      ...{ consumed: true },
    };
  }

  async findByUid(uid: string) {
    if (!(this.model instanceof Session)) throw new Error('Not Session model used')

    const found = await this.model.findOne({ where: { uid } } as any);
    if (!found) return undefined;
    return {
      ...found.data,
      ...{ consumed: true },
    };
  }

  async destroy(id: string) {
    await this.model.destroy({ where: { id } });
  }

  async consume(id: string) {
    await this.model.update({ consumedAt: new Date() }, { where: { id } });
  }

  async revokeByGrantId(grantId: string) {
    if (!(this.model instanceof Session)) throw new Error('Not Grantable type model used')

    await this.model.destroy({ where: { grantId } } as any);
  }

  public setModel(m: typeof BaseGrant) {
    this.model = m;
  }
}
