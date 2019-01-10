package org.microservicecloud.feign.client.controller;

import java.util.List;

import org.microservicecloud.api.entity.Dept;
import org.microservicecloud.api.entity.User;
import org.microservicecloud.feign.client.servie.DeptWSService;
import org.microservicecloud.feign.client.servie.UserWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientControler {

	@Autowired
	private UserWSService userWSService;
	@Autowired
	private DeptWSService deptWSService;

	@RequestMapping("/add")
	public int add(User user) {
		return userWSService.add(user);
	}

	@RequestMapping("/findAllUser")
	public List<User> findAllUser(int pageNum, int pageSize) {
		return userWSService.findAllUser(pageNum, pageSize);
	}
	
	@RequestMapping("/addDept/{depName}/{dbName}")
	public  int addDept(@PathVariable("depName")String depName,
			@PathVariable("dbName")String dbName) {
		return deptWSService.add(depName, dbName);
	}
	@RequestMapping("/findById/{id}")
	 public Dept findById(@PathVariable("id")int id)  {
		return deptWSService.findById(id);
	}
}
