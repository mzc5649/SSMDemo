package com.mzc.dao;

import com.mzc.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return 返回用户的集合
     */
    List<User> findAllUser();

    /**
     * 根据username查询用户
     * @param username
     * 若集合为空或null返回空值
     * 若集合大于1 则数据错误抛出异常
     * @return
     */
    List<User> findUserByUsername(String username);

    /**
     * 根据id查询用户
     * @param id
     * 若集合为空或null返回空值
     * 若集合大于1 则数据错误抛出异常
     * @return
     */
    List<User> findUserById(int id);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);
    /**
     * 根据Id删除用户信息
     * @param id
     */
    void deleteUserById(int id);
    /**
     * 添加账户
     * @param user
     */
    boolean addUser(User user);

}
