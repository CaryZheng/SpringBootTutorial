package com.zzb.tutorial.mybatisdemo.common;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * 自定义 Mybatis 插件，自动设置 createTime 和 updatTime 的值。
 * 拦截 update 操作（添加和修改）
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class CustomInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        // 获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getAnnotation(CreateTime.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
                    field.setAccessible(true);
                    field.set(parameter, new Date());
                }
            }

            if (field.getAnnotation(UpdateTime.class) != null) { // insert 或 update 语句插入 updateTime
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, new Date());
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

//@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
//})
//public class CustomInterceptor implements Interceptor {
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//        Object object = invocation.getArgs()[1];
//        //sql类型
//        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
//        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
//            //插入操作时，自动插入env
//            Field fieldCreate = object.getClass().getDeclaredField("create_time");
//            fieldCreate.setAccessible(true);
//            fieldCreate.set(object, new Date());
//        }else{
//            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
//                //update时，自动更新updated_at
//                Field fieldUpdate = object.getClass().getDeclaredField("updated_time");
//                fieldUpdate.setAccessible(true);
//                fieldUpdate.set(object, new Date());
//            }
//        }
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}