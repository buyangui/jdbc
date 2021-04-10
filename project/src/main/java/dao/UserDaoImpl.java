//package dao;
//
//import io.Resources;
//import org.dom4j.DocumentException;
//import pojo.User;
//import sqlSession.SqlSession;
//import sqlSession.SqlSessionFactory;
//import sqlSession.SqlSessionFactoryBuilder;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyVetoException;
//import java.io.InputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.SQLException;
//import java.util.List;
//
//public class UserDaoImpl implements IUserDao{
//
//    /*@Override
//    public List<User> findAll() throws Exception {
//        //传递路径  解析成字节输入流
//        InputStream input =  Resources.getResourceAsSteam("SqlMapConfig.xml");
//        //传入流 调用build方法
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
//
//        SqlSession sqlSession = sqlSessionFactory.OpenSession();
//
//        List<User> user3 = sqlSession.selectList("user.selectList");
//        return user3;
//    }
//
//    @Override
//    public User findByCondtion(User user) throws Exception {
//        //传递路径  解析成字节输入流
//        InputStream input =  Resources.getResourceAsSteam("SqlMapConfig.xml");
//        //传入流 调用build方法
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
//
//        SqlSession sqlSession = sqlSessionFactory.OpenSession();
//
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        return  user2;
//    }*/
//}
