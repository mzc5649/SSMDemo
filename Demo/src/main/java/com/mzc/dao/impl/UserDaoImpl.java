package com.mzc.dao.impl;

import com.mzc.dao.IUserDao;
import com.mzc.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository(value = "userDao")
public class UserDaoImpl implements IUserDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<User> findAllUser() {

        try{
            List<User> list = sqlSessionTemplate.selectList("com.mzc.dao.IUserDao.findAllUser");
            return list;
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public List<User> findUserByUsername(String username) {
        try{
            List<User> list = sqlSessionTemplate.selectList("com.mzc.dao.IUserDao.findUserByUsername",username);
            if(null==list||list.size()==0){
                return  null;
            }
            if(list.size()>1){
                throw new RuntimeException("数据不唯一，错误");
            }
            return list;
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public List<User> findUserById(int id) {
        try{
            List<User> list = sqlSessionTemplate.selectList("com.mzc.dao.IUserDao.findUserById",id);
            return list;
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public void updateUser(User user) {
        try{
            sqlSessionTemplate.update("com.mzc.dao.IUserDao.updateUser",user);
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public void deleteUserById(int id) {
        try{
            sqlSessionTemplate.delete("com.mzc.dao.IUserDao.deleteUserById",id);
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public boolean addUser(User user) {
        try{
            sqlSessionTemplate.insert("com.mzc.dao.IUserDao.addUser",user);
            return true;
        }catch(Exception t){
            return false;
        }
    }
}
