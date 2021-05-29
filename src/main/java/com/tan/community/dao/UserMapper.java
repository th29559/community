package com.tan.community.dao;

import com.tan.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 谭浩
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}
