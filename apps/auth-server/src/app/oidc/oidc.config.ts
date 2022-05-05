export default {
  scopes: ['api', 'openid', 'profile'],
  claims: {
    address: ['address'],
    email: ['email', 'email_verified'],
    // phone: ['phone_number', 'phone_number_verified'],
    profile: [
      'birthdate',
      'family_name',
      'gender',
      'locale',
      // 'middle_name',
      'name',
      'picture',
      // 'preferred_username',
      'profile',
      'updated_at',
      // 'website',
      // 'zoneinfo',
    ],
  },
  cookies: { keys: ['openid_app'] },
  features: {
    clientCredentials: { enabled: true },
    // introspection: { enabled: true },
  },
};
