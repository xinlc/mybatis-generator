package com.demo.service.impl;

import com.demo.dao.${className}Dao;
import com.demo.model.${className};
import com.demo.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Dao ${classLowerName}Dao;


    @Override
    public List<${className}> list${className}() {
        return ${classLowerName}Dao.list${className}();
    }

    @Override
    public ${className} select${className}Primary(${className} ${classLowerName}) {
        return ${classLowerName}Dao.select${className}Primary(${classLowerName});
    }
}
