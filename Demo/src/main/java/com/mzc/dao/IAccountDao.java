package com.mzc.dao;

import com.mzc.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户
     *
     * @return 返回账户的集合
     */
    List<Account> findAllAccount();

    /**
     * 根据name查询账户
     * @param name
     * 若集合为空或null返回空值
     * 若集合大于1 则数据错误抛出异常
     * @return
     */
    List<Account> findAccountByName(String name);

    /**
     * 根据id查询账户
     * @param id
     * 若集合为空或null返回空值
     * 若集合大于1 则数据错误抛出异常
     * @return
     */
    List<Account> findAccountById(int id);

    /**
     * 更新账户信息
     * @param account
     */
    void updateAccount(Account account);
    /**
     * 根据Id删除账户信息
     * @param id
     */
    void deleteAccountById(int id);

    /**
     * 添加账户
     * @param account
     */
    void addAccount(Account account);
}
