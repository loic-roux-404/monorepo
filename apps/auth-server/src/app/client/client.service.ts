import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/sequelize';
import { ClientMetadata } from 'oidc-provider';
import { ClientMeta } from './client.model';

@Injectable()
export class ClientService {
  constructor(
    @InjectModel(ClientMeta)
    private readonly clientModel: typeof ClientMeta,
  ) {}

  async create(createUserDto: ClientMetadata): Promise<ClientMeta> {
    return this.clientModel.create(createUserDto);
  }

  async findAll(): Promise<ClientMeta[]> {
    return this.clientModel.findAll();
  }

  findOne(id: string): Promise<ClientMeta> {
    return this.clientModel.findOne({
      where: {
        id,
      },
    });
  }

  async remove(id: string): Promise<void> {
    const user = await this.findOne(id);
    await user.destroy();
  }
}
