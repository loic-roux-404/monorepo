import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';
import {
  CIBADeliveryMode,
  ClientAuthMethod,
  ClientMetadata,
  EncryptionAlgValues,
  EncryptionEncValues,
  JWKS,
  ResponseType,
  SigningAlgorithm,
  SigningAlgorithmWithNone,
  SubjectTypes,
} from 'oidc-provider';
import {
  Column,
  Model,
  Table,
  AllowNull,
  DataType,
  CreatedAt,
  UpdatedAt,
} from 'sequelize-typescript';
import { getRepoKey } from '../utils/db.utils';

@Table({ tableName: 'ClientMeta' })
export class ClientMeta extends Model<ClientMeta> implements ClientMetadata {
  @ApiProperty()
  @Column
  client_id: string;

  @ApiProperty()
  @Column
  client_secret?: string;

  @ApiProperty()
  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  redirect_uris?: string[] | undefined;

  @ApiPropertyOptional()
  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  grant_types?: string[] | undefined;

  @ApiProperty({ isArray: true })
  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  response_types?: ResponseType[] | undefined;

  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  application_type?: 'web' | 'native' | undefined;

  @AllowNull
  @Column
  client_id_issued_at?: number | undefined;

  @AllowNull
  @Column
  @ApiProperty()
  client_name?: string | undefined;

  @AllowNull
  @Column
  client_secret_expires_at?: number | undefined;

  @AllowNull
  @Column
  client_uri?: string | undefined;

  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  contacts?: string[] | undefined;

  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  default_acr_values?: string[] | undefined;

  @AllowNull
  @Column
  default_max_age?: number | undefined;

  @AllowNull
  @Column(DataType.STRING)
  id_token_signed_response_alg?: SigningAlgorithmWithNone | undefined;

  @AllowNull
  @Column
  initiate_login_uri?: string | undefined;

  // jwks disabled
  @AllowNull
  @Column
  jwks_uri?: string | undefined;

  // TODO test
  @AllowNull
  @Column(DataType.JSONB)
  jwks?: JWKS | undefined;

  @AllowNull
  @Column
  logo_uri?: string | undefined;

  @AllowNull
  @Column
  policy_uri?: string | undefined;

  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  post_logout_redirect_uris?: string[] | undefined;

  @AllowNull
  @Column
  require_auth_time?: boolean | undefined;

  @AllowNull
  @Column
  scope?: string | undefined;

  @AllowNull
  @Column
  sector_identifier_uri?: string | undefined;

  @AllowNull
  @Column(DataType.STRING)
  subject_type?: SubjectTypes | undefined;

  @AllowNull
  @Column(DataType.STRING)
  token_endpoint_auth_method?: ClientAuthMethod | undefined;

  @AllowNull
  @Column
  tos_uri?: string | undefined;

  @AllowNull
  @Column(DataType.STRING)
  tls_client_auth_subject_dn?: string | undefined;

  @AllowNull
  @Column(DataType.STRING)
  tls_client_auth_san_dns?: string | undefined;

  @AllowNull
  @Column
  tls_client_auth_san_uri?: string | undefined;

  @AllowNull
  @Column
  tls_client_auth_san_ip?: string | undefined;

  @AllowNull
  @Column
  tls_client_auth_san_email?: string | undefined;

  @AllowNull
  @Column(DataType.STRING)
  token_endpoint_auth_signing_alg?: SigningAlgorithm | undefined;

  @AllowNull
  @Column(DataType.STRING)
  userinfo_signed_response_alg?: SigningAlgorithmWithNone | undefined;

  @AllowNull
  @Column(DataType.STRING)
  introspection_signed_response_alg?: SigningAlgorithmWithNone | undefined;

  @AllowNull
  @Column(DataType.STRING)
  introspection_encrypted_response_alg?: EncryptionAlgValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  introspection_encrypted_response_enc?: EncryptionEncValues | undefined;

  @AllowNull
  @Column
  backchannel_logout_session_required?: boolean | undefined;

  @AllowNull
  @Column
  backchannel_logout_uri?: string | undefined;

  @AllowNull
  @Column(DataType.STRING)
  request_object_signing_alg?: SigningAlgorithmWithNone | undefined;

  @AllowNull
  @Column(DataType.STRING)
  request_object_encryption_alg?: EncryptionAlgValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  request_object_encryption_enc?: EncryptionEncValues | undefined;

  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  request_uris?: string[] | undefined;

  @AllowNull
  @Column(DataType.STRING)
  id_token_encrypted_response_alg?: EncryptionAlgValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  id_token_encrypted_response_enc?: EncryptionEncValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  userinfo_encrypted_response_alg?: EncryptionAlgValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  userinfo_encrypted_response_enc?: EncryptionEncValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  authorization_signed_response_alg?: SigningAlgorithm | undefined;

  @AllowNull
  @Column(DataType.STRING)
  authorization_encrypted_response_alg?: EncryptionAlgValues | undefined;

  @AllowNull
  @Column(DataType.STRING)
  authorization_encrypted_response_enc?: EncryptionEncValues | undefined;

  @AllowNull
  @Column(DataType.ARRAY(DataType.STRING))
  web_message_uris?: string[] | undefined;

  @AllowNull
  @Column
  tls_client_certificate_bound_access_tokens?: boolean | undefined;

  @AllowNull
  @Column
  require_signed_request_object?: boolean | undefined;

  @AllowNull
  @Column
  require_pushed_authorization_requests?: boolean | undefined;

  @AllowNull
  @Column
  backchannel_user_code_parameter?: boolean | undefined;

  @AllowNull
  @Column
  backchannel_authentication_request_signing_alg?: string | undefined;

  @AllowNull
  @Column
  backchannel_client_notification_endpoint?: string | undefined;

  @AllowNull
  @Column(DataType.STRING)
  backchannel_token_delivery_mode?: CIBADeliveryMode | undefined;

  [key: string]: unknown;

  @CreatedAt
  createdAt: Date;

  @UpdatedAt
  updatedAt: Date;
}

export const ClientMetaRepository = {
  provide: getRepoKey(ClientMeta.name),
  useValue: ClientMeta,
};
