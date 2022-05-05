import { Module } from '@nestjs/common';

import { OidcConfigService } from './oidc-config.service';
import { OidcAdapterModule } from '../oidc-adapter/adapter.module';
import { SequelizeModule } from '@nestjs/sequelize';
import GrantsModel from './grant.model';
import { ClientMetaRepository } from '../client/client.model';

@Module({
  imports: [SequelizeModule.forFeature(GrantsModel), OidcAdapterModule],
  providers: [OidcConfigService, ClientMetaRepository],
  exports: [SequelizeModule, OidcConfigService],
})
export class OidcConfigModule {}
