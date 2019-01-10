package org.microservicecloud.feign.client.servie;

import java.util.List;

import org.microservicecloud.api.constant.ApplicationName;
import org.microservicecloud.api.constant.ZuulName;
import org.microservicecloud.api.entity.User;
import org.microservicecloud.feign.client.hystric.UserWSServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ApplicationName.SERVER_USER, fallback = UserWSServiceHystric.class)
public interface UserWSService {

	@RequestMapping(value=ZuulName.ZULL_USER+"/add",method = RequestMethod.GET)
	public int add(@RequestBody User user);

	@RequestMapping(ZuulName.ZULL_USER+"/findAllUser")
	public List<User> findAllUser(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize")int pageSize);
}
