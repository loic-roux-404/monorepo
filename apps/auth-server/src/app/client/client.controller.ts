import { Controller, Delete, Get, Param, Post } from '@nestjs/common';
import { ApiBody } from '@nestjs/swagger';
import { ClientMetadata } from 'oidc-provider';
import { ClientMeta } from './client.model';
import { ClientService } from './client.service';

@Controller('client')
export class ClientController {
  constructor(private readonly clientService: ClientService) {}

  @Post()
  @ApiBody({ type: ClientMeta })
  create(createUserDto: ClientMetadata): Promise<ClientMeta> {
    return this.clientService.create(createUserDto);
  }

  @Get()
  findAll(): Promise<ClientMeta[]> {
    return this.clientService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string): Promise<ClientMeta> {
    return this.clientService.findOne(id);
  }

  @Delete(':id')
  remove(@Param('id') id: string): Promise<void> {
    return this.clientService.remove(id);
  }
}
