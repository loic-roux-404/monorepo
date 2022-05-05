import { Column, DataType, Model, Table } from 'sequelize-typescript';

export class BaseGrant extends Model<BaseGrant> {

  @Column(DataType.JSONB)
  data!: object;

  @Column
  consumedAt: Date;

  @Column
  expiresAt: Date;
}

export class Grantable extends BaseGrant {
  @Column
  grantId: string;
}

@Table
export class AccessTokenClient extends BaseGrant {}

@Table
export class Interaction extends BaseGrant {}

@Table
export class ReplayDetection extends BaseGrant {}

@Table
export class PushedAuthorizationRequest extends BaseGrant {}

@Table
export class Grant extends BaseGrant {}

@Table
export class InitialAccessToken extends BaseGrant {}

@Table
export class Session extends BaseGrant {
  @Column
  uid: string;
}

@Table
export class AuthorizationCode extends Grantable {}

@Table
export class RefreshToken extends Grantable {}

@Table
export class AccessToken extends Grantable {}

@Table
export class DeviceCode extends Grantable {
  @Column
  userCode: string;
}

@Table
export class BackchannelAuthenticationRequest extends Grantable {}

export default [
  AccessTokenClient,
  AuthorizationCode,
  RefreshToken,
  Session,
  AccessToken,
  InitialAccessToken,
  DeviceCode,
  Interaction,
  ReplayDetection,
  PushedAuthorizationRequest,
  Grant,
  BackchannelAuthenticationRequest
]
