package sqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

    //查询所有
    public <E> List<E> selectList(String statementId,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    //查询单个
    public <T> T selectOne(String statementId,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException;


    //生成代理对象接口
    public <T> T getMapper(Class<?> mappClass);

    //增加操作
    public void querySql(String statementId, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;

//    //更新操作
//    public void update(String statementId, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;
//
//    //删除
//    public void delete(String statementId, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;
}
