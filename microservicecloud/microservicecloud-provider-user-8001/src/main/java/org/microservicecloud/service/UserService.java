package org.microservicecloud.service;

import java.util.List;

import org.microservicecloud.api.entity.User;

public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
