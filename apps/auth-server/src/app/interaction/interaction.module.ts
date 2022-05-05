import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios';
import { InteractionController } from './interaction.controller';

@Module({
  imports: [HttpModule],
  controllers: [InteractionController],
  providers: [],
})
export class InteractionModule {}
