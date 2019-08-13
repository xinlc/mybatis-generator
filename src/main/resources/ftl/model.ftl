package com.demo.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
<#if isDate == true>
import java.util.Date;
</#if>
<#if isNumber == true>
import java.math.BigDecimal;
</#if>

@Data
@ApiModel
@TableName(value = "${tableName}")
public class ${className}{
<#list primaries as value>

    @TableId(value = "${value.fieldName}", type = IdType.AUTO)
    @ApiModelProperty("${value.comments}")
    private ${value.fieldType} ${value.fieldName};
</#list>
<#list fields as value>

    @ApiModelProperty("${value.comments}")
    private ${value.fieldType} ${value.fieldName};
</#list>

}