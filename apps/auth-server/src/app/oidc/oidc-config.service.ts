import {
  OidcModuleOptions,
  OidcModuleOptionsFactory,
} from 'nest-oidc-provider';
import { Injectable } from '@nestjs/common';
import { AdapterFactory } from 'oidc-provider';
import oidc from './oidc.config';
import { OPEN_ID_PATH, OPEN_ID_ISSUER } from '../environment';
import { OidcAdapter } from '../oidc-adapter/oidc.adapter';
import { AdapterService } from '../oidc-adapter/oidc.adapter.service';
import { InjectModel } from '@nestjs/sequelize';
import { ClientMeta } from '../client/client.model';

@Injectable()
export class OidcConfigService implements OidcModuleOptionsFactory {
  constructor(
    private readonly adapterService: AdapterService,
    @InjectModel(ClientMeta) private clientModel: typeof ClientMeta
  ) {}

  async createModuleOptions(): Promise<OidcModuleOptions> {
    const criticalQueryOptions = { retry: { max: 10, match: [] }}
    return {
      issuer: OPEN_ID_ISSUER,
      path: OPEN_ID_PATH,
      oidc: {
        ...oidc,
        ...{ clients: await this.clientModel.findAll(criticalQueryOptions) },
      },
    };
  }

  createAdapterFactory?(): AdapterFactory {
    return (modelName: string) =>
      new OidcAdapter(modelName, this.adapterService);
  }
}
