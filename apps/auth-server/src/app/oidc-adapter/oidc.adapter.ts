
import { wInject } from '@nestjs/common';
import { Adapter, AdapterPayload } from 'oidc-provider';
import { getRepoKey } from '../utils/db.utils';
import { BaseGrant } from '../oidc/grant.model';
import { AdapterService } from './oidc.adapter.service';

export class OidcAdapter implements Adapter {
  private model: typeof BaseGrant;

  constructor(
    modelName: string,
    private sqlAdapterService: AdapterService
  ) {
    wInject(getRepoKey(modelName))(this.model, this.model.constructor.name);
    this.sqlAdapterService.setModel(this.model)
  }

  async upsert(
    id: string,
    payload: AdapterPayload,
    expiresIn: number,
  ): Promise<void> {
    this.sqlAdapterService.upsert(id, payload, expiresIn);
  }

  async find(id: string): Promise<void | AdapterPayload> {
    return this.sqlAdapterService.find(id);
  }

  async findByUserCode(userCode: string): Promise<void | AdapterPayload> {
    return this.sqlAdapterService.findByUserCode(
      userCode,
    );
  }

  async findByUid(uid: string): Promise<void | AdapterPayload> {
    return this.sqlAdapterService.findByUid(uid);
  }

  async consume(id: string): Promise<void> {
    this.sqlAdapterService.consume(id);
  }

  async destroy(id: string): Promise<void> {
    this.sqlAdapterService.destroy(id);
  }

  async revokeByGrantId(grantId: string): Promise<void> {
    this.sqlAdapterService.revokeByGrantId(grantId);
  }
}
