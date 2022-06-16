import mxRequest from '../index';

// 登录接口
export function loginRequest(username, password) {
  return mxRequest.post({
    url: 'user/login',
    data: {
      username,
      password
    }
  });
}

// 注册接口
export function registerRequest(username, password) {
  return mxRequest.post({
    url: 'user/register',
    data: {
      username,
      password
    }
  })
}


