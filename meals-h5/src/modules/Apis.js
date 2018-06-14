let baseURL = ''

switch (process.env.NODE_ENV) {
  case 'development':
    baseURL = 'http://localhost:8081'
    break
  case 'production':
    baseURL = 'http://106.14.205.164'
    break
  default:
    baseURL = ''
}

baseURL += '/meals'

const apis = {
  sendSms: '/sendSms',
  loginByPhone: '/user/loginByPhone',
  updateUser: '/user/updateUser',
  getUserInfo: '/user/getUserInfo',
  getMerchantList: '/getMerchantList',
  getMealsByMchId: '/getMealsList',
  submitOrder: '/submitOrder',
  getUserOrders: '/getUserOrders',
  getTodayOrders: '/getTodayOrders',
  viewCache: '/cache/stats/all'
}

Object.keys(apis).forEach((key) => {
  const value = apis[key];
  apis[key] = baseURL + value;
})

export default apis
