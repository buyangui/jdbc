package sqlSession;

import pojo.Configuration;
import pojo.MapperStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Executor {

    public <E> List<E> quyer(Configuration configuration, MapperStatement mapperStatement,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException;
}
