package com.mzc.service.impl;
import com.mzc.dao.IUserDao;
import com.mzc.domain.User;
import com.mzc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
     @Qualifier(value = "userDao")
    IUserDao userDao;
    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public List<User> findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }
}
