package sqlSession;

import pojo.Configuration;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        //调用executor接口中的quyer方法实现数据查询
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        //MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
        List<Object> list =  simpleExecutor.quyer(configuration,configuration.getMapperStatementMap().get(statementId),params);
        //将要去完成对simpleExecutor里的query方法的调用
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Object> obj = selectList(statementId, params);   //查询一个就是根据条件查询多个
        if(obj.size()==1){
            return (T) obj.get(0);
        }else{
            throw new RuntimeException("查询结果不正确");
        }
    }

    @Override
    public void querySql(String statementId, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        //调用executor接口中的quyer方法实现数据查询
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        //MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
        List<Object> list =  simpleExecutor.quyer(configuration,configuration.getMapperStatementMap().get(statementId),params);
    }





    //生成代理对象
    @Override
    public <T> T getMapper(Class<?> mappClass) {
        Object proxy = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mappClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //重写invoke  proxy=当前代理对象的引用  method=当前倍调用方法的引用  args=传递的参数
                //sql标识 statement.id  = 接口的全限定名.方法名
                String methodName = method.getName(); //方法名
                String className = method.getDeclaringClass().getName();   //接口全限定名
                String statementId=className+"."+methodName;

                //参数  是传入的参数 =args

                //调用执行，判断执行操作   根据返回的类型判断是调用selectList 还是selectOne
                Type genericReturnType = method.getGenericReturnType();
                //判断是否进行了 泛型类型参数化   ？
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> list =selectList(statementId,args);
                    return list;
                }else if(genericReturnType.getTypeName().equals("void") ){
                    querySql(statementId,args);
                    return null;
                }else{
                    return selectOne(statementId,args);
                }



            }
        });
        return (T) proxy;
    }
}
