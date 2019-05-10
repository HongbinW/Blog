package com.hongbinw.blog.repository;

import com.hongbinw.blog.domain.User;

import java.util.List;

public interface UserRepository {
    //创建或修改用户
    User saveOrUpdateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> listUsers();
}
