package dao;

import org.dom4j.DocumentException;
import pojo.User;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    public List<User> findAll() throws  Exception;


    public User findByCondtion(User user) throws Exception;

    public void saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(Integer id);
}
