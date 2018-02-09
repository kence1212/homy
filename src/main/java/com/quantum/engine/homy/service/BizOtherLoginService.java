package com.quantum.engine.homy.service;

import java.util.Map;

public interface BizOtherLoginService {

	Map<String,Object> login(String openid, String unionid, int type, String portrait, String nickname);

	Map<String,Object> bindPhone(String phone, String code, String loginToken, Integer otherLoginId, String ip);

}
