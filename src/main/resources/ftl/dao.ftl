package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.${className};
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {

    void delete${className}ById(Long id);
    
}