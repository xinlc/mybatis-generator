<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.demo.mapper.${className}Mapper">

    <update id="delete${className}ById" parameterType="java.lang.Long">
        update ${tableName} set delete_flag = '0' where id = ${'#'}${'{'}id${'}'}
    </update>
</mapper>
