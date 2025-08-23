package com.lilac.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
)})
public class AutoFillInterceptor implements Interceptor {
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        if (parameter != null) {
            LocalDateTime now = LocalDateTime.now();

            if (sqlCommandType == SqlCommandType.INSERT) {
                // INSERT
                setFieldIfNull(parameter, CREATE_TIME, now);
                setFieldIfNull(parameter, UPDATE_TIME, now);
            } else if (sqlCommandType == SqlCommandType.UPDATE) {
                // UPDATE
                setField(parameter, UPDATE_TIME, now);
            }
        }
        return invocation.proceed();
    }

    /**
     * 强制为对象的指定字段设置值 (无条件覆盖)
     */
    private void setField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value); // 直接设置值
        } catch (NoSuchFieldException e) {
            // 实体没有这个字段，是正常情况，静默处理
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to set field by reflection", e);
        }
    }

    /**
     * 仅当字段值为 null 时，才为对象的指定字段设置值
     */
    private void setFieldIfNull(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            if (field.get(target) == null) { // 检查是否为 null
                field.set(target, value);
            }
        } catch (NoSuchFieldException e) {
            // 实体没有这个字段，是正常情况，静默处理
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to set field by reflection", e);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
