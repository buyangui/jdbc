package sqlSession;

import config.XMLConfigBuilder;
import org.dom4j.DocumentException;
import pojo.Configuration;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //使用dom4j解析配置文件，将解析结果封装道configuration中
         XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
         Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

         //创建sqlsessionFactory对象，生产sqlsession会话，工厂模式
         //底层需要了拿到sql连接数据库执行，所以传递configuration
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }
}
