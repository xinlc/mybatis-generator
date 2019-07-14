package com.demo.service;

import com.demo.mapper.${className}Mapper;
import com.demo.model.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${className}Service {

    @Autowired
    private ${className}Mapper ${classLowerName}Mapper;

    public List<${className}> get${className}List() {
         return ${classLowerName}Mapper.selectList(null);
    }

    public void add${className}(${className} ${classLowerName}) {
        int result = ${classLowerName}Mapper.insert(${classLowerName});
        if(result<1){
            throw new RuntimeException("添加失败");
         }
    }

    public void update${className}(${className} ${classLowerName}) {
        ${classLowerName}Mapper.updateById(${classLowerName});
    }

    public ${className} find${className}ById(Long id) {
         return ${classLowerName}Mapper.selectById(id);
    }

    public void delete${className}ById(Long id) {
         ${classLowerName}Mapper.delete${className}ById(id);
    }
}
