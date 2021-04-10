package sqlSession;

import config.BoundSql;
import pojo.Configuration;
import pojo.MapperStatement;
import utils.GenericTokenParser;
import utils.ParameterMapping;
import utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;

public class SimpleExecutor implements Executor{
    @Override
    public <E> List<E> quyer(Configuration configuration, MapperStatement mapperStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException {
        //实现jdbc操作

        DataSource datasource = configuration.getDatasource();
        //获取驱动
        Connection connection = datasource.getConnection();
        //获取sql信息  配置文件中的原sql 包含占位符  #{}  需转换成？
        String sql = mapperStatement.getSql();
        //转换 解析sql
        BoundSql boundSql = getBoundSql(sql);

        //预处理对象 preparedStatment
        PreparedStatement preparedStatment = connection.prepareStatement(boundSql.getSqlText());
        //处理设置传入的参数
        String paramteType = mapperStatement.getParamteType();    //获取参数的全路径

        Class<?> TypeClass = getClassType(paramteType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i=0;i<parameterMappingList.size();i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //解析参数
            Object o;
            //判断只要是一个参数，不用解析对应的位置 ，直接查询
            if(parameterMappingList.size()!=1){
                //反射根据属性名获取属性
                Field declaredField = TypeClass.getDeclaredField(content);
                //设置暴力访问
                declaredField.setAccessible(true);
                //解析参数
               o = declaredField.get(params[0]);
                //设置参数完成
            }else{
                o =params[0];
            }

            preparedStatment.setObject(i+1,o);
        }
        //执行sql  返回结果在resultSet

        String resultType = mapperStatement.getResultType();
        if(resultType==null){  //无返回结果，做update insert delete操作
            preparedStatment.executeUpdate();
            return null;
        }
            ResultSet resultSet = preparedStatment.executeQuery();



        //根据路径变成对象
        Class<?> resultTypeClass = getClassType(resultType);


        //返回集合
        List<Object> objects = new ArrayList<>();
        //封装返回的结果集
        while(resultSet.next()){
            //获取具体实现
            Object anInterface = resultTypeClass.newInstance();
            //获取源数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for(int i=1;i<=metaData.getColumnCount();i++){    //遍历查询结果列数
               //从1开始，获取列名
                String columnName = metaData.getColumnName(i);
                //根据列名获取属性值
                Object col = resultSet.getObject(columnName);

                //使用内省、反射，根据数据库中的属性名和实体类的队友，做结果封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(anInterface,col);
            }
            objects.add(anInterface);
        }

        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramteType) throws ClassNotFoundException {
        if(paramteType!=null){
            Class<?> tClass = Class.forName(paramteType);
            return  tClass;
        }
        return null;
    }


    /* 完成 #{}转换？ ---解析#{}里的值*/
    private BoundSql getBoundSql(String sql) {
        //借助工具类
        //标记处理器：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        //解析后的slq语句
        String parse = genericTokenParser.parse(sql);
        //解析的参数
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parse,parameterMappings);

        return boundSql;
    }
}
