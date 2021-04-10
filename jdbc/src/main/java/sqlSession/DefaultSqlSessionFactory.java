package sqlSession;

import pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration=configuration;
    }

    @Override
    public SqlSession OpenSession() {
        return new DefaultSqlSession(configuration);
    }
}
