package com.quantum.engine.homy.dao;


import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BizUserCompany;
import com.quantum.engine.homy.model.User;

/**
 * 用户dao 
 * @author nicya
 * @date 2014年12月31日 下午4:42:02
 */
public interface UserMapper extends BaseMapper {
    
    public User getByPhone(String account);
    
    public User getByUsername(String username);
    
    public void changePassword(User user);
    
    public List<User> list(Map<String, Object> params);
    
    public Long count(Map<String, Object> params);
    
    public BizUserCompany getCompanyByUserId(Integer userId);
    
    public BizUserCompany getOnlyExtByUserId(Integer userId);
    
    public void updateCompany(BizUserCompany company);
    
    public void insertCompany(BizUserCompany company);
    
    public User getByUserId(Integer userId);

}
