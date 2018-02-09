package com.quantum.engine.homy.dao;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.UserToken;

/**
 * 
 * @ClassName: UserTokenMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author nicya
 * @date 2017年11月7日 下午3:55:20 
 *
 */
public interface UserTokenMapper extends BaseMapper {
	
	public UserToken getByToken(String token);

	public UserToken getByTokenAndUserId(@Param("token")String token, @Param("userId")Integer userId);

}
