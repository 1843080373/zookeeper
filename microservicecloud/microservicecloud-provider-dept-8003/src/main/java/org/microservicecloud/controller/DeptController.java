package org.microservicecloud.controller;

import org.microservicecloud.api.entity.Dept;
import org.microservicecloud.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class DeptController {
	private static Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	private DeptService deptService;

	@RequestMapping("/add/{depName}/{dbName}")
	public  int add(@PathVariable("depName")String depName,
			@PathVariable("dbName")String dbName) {
		Dept dept=new Dept(depName, dbName);
		logger.info(JSONObject.toJSONString(dept));
		return deptService.addEntity(dept);
	}
	@RequestMapping("/findById/{id}")
	 public Dept findById(@PathVariable("id")int id)  {
		return deptService.findById(id);
	}
}
