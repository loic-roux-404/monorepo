import { ClientService } from './client.service';
import { ClientController } from './client.controller';
import { Module } from '@nestjs/common';
import { ClientMeta } from './client.model';
import { SequelizeModule } from '@nestjs/sequelize';
import { getRepoKey } from '../utils/db.utils';

@Module({
  imports: [SequelizeModule.forFeature([ClientMeta])],
  controllers: [ClientController],
  providers: [ClientService],
  exports: [SequelizeModule]
})
export class ClientModule {}
