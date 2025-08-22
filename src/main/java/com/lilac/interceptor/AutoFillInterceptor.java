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
        method = "update", // 拦截 Executor 的 update 方法
        args = {MappedStatement.class, Object.class}
)})
public class AutoFillInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取 MappedStatement 和 参数对象
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        // 获取 SQL 命令类型 (INSERT, UPDATE)
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (parameter != null) {
            LocalDateTime now = LocalDateTime.now();
            if (sqlCommandType == SqlCommandType.INSERT) {
                // INSERT 操作，填充 createTime 和 updateTime
                setField(parameter, "createTime", now);
                setField(parameter, "updateTime", now);
            } else if (sqlCommandType == SqlCommandType.UPDATE) {
                // UPDATE 操作，仅填充 updateTime
                setField(parameter, "updateTime", now);
            }
        }
        // 执行原始方法
        return invocation.proceed();
    }

    /**
     * 使用反射为对象的指定字段设置值
     *
     * @param target    目标对象
     * @param fieldName 字段名
     * @param value     值
     */
    private void setField(Object target, String fieldName, Object value) {
        try {
            // 获取字段
            Field field = target.getClass().getDeclaredField(fieldName);
            // 设置为可访问
            field.setAccessible(true);
            // 如果字段当前值为 null，才设置新值
            if (field.get(target) == null) {
                field.set(target, value);
            }
        } catch (NoSuchFieldException e) {
            // 如果实体没有这个字段，不做任何事
        } catch (IllegalAccessException e) {
            // 日志记录或其他异常处理
            throw new RuntimeException("Failed to set field by reflection", e);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以接收插件配置属性
    }
}