package com.ywmobile.app.internship.DAO;

import com.ywmobile.app.internship.MyBatisUtil;
import com.ywmobile.app.internship.model.Department;
import com.ywmobile.app.internship.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public class UserDAO {

    public Optional<User> findByUsername(String username) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        User user = session.selectOne("UserMapper.findByUsername", username);
        session.commit();
        session.close();
        return Optional.ofNullable(user);
    }

    public User findById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        User user = session.selectOne("UserMapper.findById", id);
        session.commit();
        session.close();
        return user;
    }

    public void save(User user){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("UserMapper.insertUser", user);
        session.commit();
        session.close();
    }

    public boolean getExistByUsername(String  username){
        boolean rs = true;
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        User user = session.selectOne("UserMapper.getExistByUsername", username);
        if(user!= null ){
            rs = false;
        }
        session.commit();
        session.close();
        return rs ;
    }


}

