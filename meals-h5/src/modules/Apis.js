let baseURL = ''

switch (process.env.NODE_ENV) {
  case 'development':
    baseURL = 'http://localhost:8081'
    break
  case 'production':
    baseURL = 'http://localhost:8081'
    break
  default:
    baseURL = ''
}

baseURL += '/meals/'

const apis = {
  sendSms: 'sendSms',
  loginByPhone: 'loginByPhone',
  getMerchantList: 'getMerchantList'
}

Object.keys(apis).forEach((key) => {
  const value = apis[key];
  apis[key] = baseURL + value;
})

export default apis
