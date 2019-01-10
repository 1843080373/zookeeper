package org.microservicecloud.feign.client.servie;

import org.microservicecloud.api.constant.ApplicationName;
import org.microservicecloud.api.constant.ZuulName;
import org.microservicecloud.api.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = ApplicationName.SERVER_DEPT)
public interface DeptWSService {

	@RequestMapping(ZuulName.ZULL_DEPT+"/add/{depName}/{dbName}")
	public int add(@PathVariable("depName") String depName, @PathVariable("dbName") String dbName);

	@RequestMapping(ZuulName.ZULL_DEPT+"/findById/{id}")
	public Dept findById(@PathVariable("id") int id);
}
