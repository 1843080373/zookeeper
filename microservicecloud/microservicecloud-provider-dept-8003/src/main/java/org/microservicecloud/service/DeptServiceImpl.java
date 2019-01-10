package org.microservicecloud.service;

import org.microservicecloud.api.entity.Dept;
import org.microservicecloud.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;//这里会报错，但是并不会影响

    @Override
    public int addEntity(Dept dept) {
        return deptMapper.insertSelective(dept);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public Dept findById(int id) {
        return deptMapper.selectByPrimaryKey(id);
    }
}