package com.example.demo.controller;

import com.example.demo.config.result.PageResp;
import com.example.demo.config.result.PageRespUtils;
import com.example.demo.config.result.ResultResp;
import com.example.demo.config.result.ResultUtils;
import com.example.demo.model.${className};
import com.example.demo.service.${className}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "用户管理")
@RestController
@RequestMapping("/v1/${classLowerName}")
public class ${className}Controller {

@Autowired
private ${className}Service ${classLowerName}Service;

    @ApiOperation("用户列表分页")
    @GetMapping("/page")
    public PageResp<${className}> page${className}List(${className} ${classLowerName},@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer limit) {
        PageHelper.startPage(page, limit);
        List<${className}> ${classLowerName}List = ${classLowerName}Service.get${className}List();
        PageInfo<${className}> pageInfo = new PageInfo<>(${classLowerName}List);
        Long count = pageInfo.getTotal();
        return PageRespUtils.success(${classLowerName}List,count);
    }

    @ApiOperation("用户添加")
    @PostMapping("/add")
    public ResultResp<Integer> add${className}(@RequestBody ${className} ${classLowerName}) {
        ${classLowerName}Service.add${className}(${classLowerName});
        return ResultUtils.success(1);
    }

    @ApiOperation("用户修改")
    @GetMapping("/update")
    public ResultResp<Integer> update${className}(@RequestBody ${className} ${classLowerName}) {
        ${classLowerName}Service.update${className}(${classLowerName});
        return ResultUtils.success(1); 
    }

    @ApiOperation("用户详情")
    @GetMapping("/detail/{id}")
    public ResultResp<${className}> find${className}ById(@PathVariable Long id) {
        ${className} ${classLowerName} = ${classLowerName}Service.find${className}ById(id);
        return ResultUtils.success(${classLowerName}); 
    }

    @ApiOperation("用户详情")
    @GetMapping("/delete/{id}")
    public ResultResp<Integer> delete${className}ById(@PathVariable Long id) {
        ${classLowerName}Service.delete${className}ById(id);
        return ResultUtils.success(1);
    }
}
