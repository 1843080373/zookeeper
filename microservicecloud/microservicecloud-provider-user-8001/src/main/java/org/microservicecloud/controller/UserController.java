package org.microservicecloud.controller;

import java.util.List;

import org.microservicecloud.api.entity.User;
import org.microservicecloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/add")
	public  int add(@RequestBody User user) {
		logger.info(JSONObject.toJSONString(user));
		return userService.addUser(user);
	}
	@RequestMapping("/findAllUser")
	public  List<User> findAllUser(int pageNum, int pageSize) {
		return userService.findAllUser(pageNum, pageSize);
	}
}
