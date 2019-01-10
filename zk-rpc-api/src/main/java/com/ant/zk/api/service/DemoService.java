package com.ant.zk.api.service;

import java.util.List;

import com.ant.zk.api.entity.User;

public interface DemoService {
	public List<User> findUsers(String name);
	public void save(User user);
}
