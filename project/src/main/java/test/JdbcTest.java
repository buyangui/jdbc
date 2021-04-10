package test;


import dao.IUserDao;
import io.Resources;
import org.junit.Test;
import pojo.User;
import sqlSession.SqlSession;
import sqlSession.SqlSessionFactory;
import sqlSession.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.util.List;

public class JdbcTest {

    @Test
    public  void test() throws Exception {
        //传递路径  解析成字节输入流
        InputStream input =  Resources.getResourceAsSteam("SqlMapConfig.xml");
        //传入流 调用build方法
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);

        SqlSession sqlSession = sqlSessionFactory.OpenSession();

       User user =  new User();
       user.setId(1);
       user.setUsername("zhangsan");
        User user2 = sqlSession.selectOne("dao.IUserDao.findByCondtion", user);
        System.out.println(user2);
        List<User> user3 = sqlSession.selectList("dao.IUserDao.findAll");
        for(User u:user3){
            System.out.println(u);
        }

//        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
////        List<User> user4 = mapper.findAll();
////        for(User u:user4){
////            System.out.println(u);
////        }
//        User user =  new User();
//        user.setId(1);
//        user.setUsername("zhangsan");
//        User user5 = mapper.findByCondtion(user);//       System.out.println(user5);
    }


    @Test
    public  void test1() throws Exception {
        //传递路径  解析成字节输入流
        InputStream input =  Resources.getResourceAsSteam("SqlMapConfig.xml");
        //传入流 调用build方法
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);

        SqlSession sqlSession = sqlSessionFactory.OpenSession();

        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user =  new User();
        user.setId(4);
        user.setUsername("ceshi");
        // mapper.saveUser(user);
        // mapper.updateUser(user);
        mapper.deleteUser(4);

    }
}
