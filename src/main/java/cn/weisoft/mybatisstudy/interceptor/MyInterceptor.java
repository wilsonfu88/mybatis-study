package cn.weisoft.mybatisstudy.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

//测试拦截器
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class}),@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
@Component
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        if(args.length>=2/*invocation.getTarget() instanceof ResultSetHandler*/) {
            MappedStatement ms = (MappedStatement) args[0];
            Object parameterObject = args[1];
            BoundSql boundSql = ms.getBoundSql(parameterObject);

            String sql = boundSql.getSql();
            System.out.println("sql:" + sql);

        }

        System.out.println("intercept " + new Date());

        System.out.println(invocation.getTarget().toString());
        Object obj =  invocation.proceed();

        return obj;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties);
    }
}
