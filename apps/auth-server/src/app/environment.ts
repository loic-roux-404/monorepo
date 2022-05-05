import { SequelizeModuleOptions } from '@nestjs/sequelize';
import { ClientMeta } from './client/client.model';
import GrantsModel from './oidc/grant.model';

const dbCnfFromEnv = {
  host: process.env?.PG_HOST || 'localhost',
  port: Number(process.env?.PG_PORT || '5432'),
  username: process.env?.PG_AUTH_USER || 'root',
  password: process.env?.PG_AUTH_PASSWORD || 'root',
  database: process.env?.PG_AUTH_DB || 'auth_server',
};

export const db: SequelizeModuleOptions = {
  ...dbCnfFromEnv,
  ...{
    dialect: 'postgres',
    retryAttempts: 5,
    retryDelay: 2000,
    synchronize: true,
    // sync: { force: true },
    autoLoadModels: true,
    models: [...GrantsModel, ClientMeta],
  },
};

const OPEN_ID_ISSUER =
    process.env.OPEN_ID_ISSUER ||
    `http://${process.env.AUTH_SERVER_IP || '0.0.0.0'}:${
      process.env.AUTH_SERVER_PORT || '3333'
    }`,
  OPEN_ID_PATH = process.env.OPEN_ID_PATH || '/oidc',
  AUTH_RESOURCE_SERVER_URL = process.env.AUTH_RESOURCE_SERVER_URL || '0.0.0.0:8082';

export { OPEN_ID_ISSUER, OPEN_ID_PATH, AUTH_RESOURCE_SERVER_URL };

export default {
  production: process.env.NODE_ENV === 'production',
  db,
};
