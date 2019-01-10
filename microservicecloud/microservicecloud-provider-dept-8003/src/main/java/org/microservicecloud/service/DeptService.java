package org.microservicecloud.service;

import org.microservicecloud.api.entity.Dept;

public interface DeptService {

    int addEntity(Dept dept);

    Dept findById(int id);
}
