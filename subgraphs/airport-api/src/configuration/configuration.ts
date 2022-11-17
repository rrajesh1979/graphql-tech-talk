export default () => ({
  env: process.env.APP_ENV || 'local',
  port: parseInt(process.env.APP_PORT, 10) || 8080,
  keyVaultUrl: process.env.KEYVAULT_URL || 'employerportal-kv',
  mongoConnectionString: process.env.MONGO_CONNECTION_STRING
});
