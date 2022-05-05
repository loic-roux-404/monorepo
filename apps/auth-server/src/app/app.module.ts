import { InteractionModule } from './interaction/interaction.module';
import { Module } from '@nestjs/common';
import { SequelizeModule } from '@nestjs/sequelize';
import { OidcModule } from 'nest-oidc-provider';
import { AppController } from './app.controller';
import { db } from './environment';
import { OidcAdapterModule } from './oidc-adapter/adapter.module';
import { OidcConfigModule } from './oidc/oidc-config.module';
import { grantsRepositories } from './oidc/grant.repository';
import { OidcConfigService } from './oidc/oidc-config.service';
import { ClientMeta, ClientMetaRepository } from './client/client.model';
import { ClientModule } from './client/client.module';

@Module({
  imports: [
    SequelizeModule.forRoot(db),
    SequelizeModule.forFeature([ClientMeta]),
    ClientModule,
    OidcModule.forRootAsync({
      useExisting: OidcConfigService,
      imports: [OidcAdapterModule, OidcConfigModule],
    }),
    InteractionModule,
  ],
  controllers: [AppController],
  providers: [ClientMetaRepository, ...grantsRepositories],
})
export class AppModule {}
