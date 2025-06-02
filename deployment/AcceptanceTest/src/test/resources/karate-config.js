function fn() {
  const uuid = java.util.UUID.randomUUID();
  const randomNum = Math.floor(Math.random() * 20) + 1;
  const config = {
    headers: {
       'Message-Id': uuid,
       'transactionId': 'test' + randomNum
    },
    urlLogin: '',
    urlBase: 'https://localhost:8081/v1',
    //urlBase: '#{AcceptanceTest-urlBase}#',
  };
  //var resultLogin = karate.callSingle('login.feature@getIdToken', config);
  //config.useridToken = resultLogin.response.id_token;
  //config.headers = ('headers', { Authorization: 'Bearer ' + config.useridToken, 'Message-Id': uuid});
  //config.headers = ('headers', { "x-api-key-dev": "*****************", 'Message-Id': uuid});
  //karate.log('id_token generado: ',  config.useridToken);
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);
  karate.configure('ssl', true);
  return config;
}
