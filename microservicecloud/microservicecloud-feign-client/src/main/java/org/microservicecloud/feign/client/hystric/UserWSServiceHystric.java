package org.microservicecloud.feign.client.hystric;

import java.util.Arrays;
import java.util.List;

import org.microservicecloud.api.entity.User;
import org.microservicecloud.feign.client.servie.UserWSService;
import org.springframework.stereotype.Component;

@Component
public class UserWSServiceHystric implements UserWSService {
    
	@Override
	public int add(User user) {
		return -1;
	}

	@Override
	public List<User> findAllUser(int pageNum, int pageSize) {
		return Arrays.asList();
	}
}
