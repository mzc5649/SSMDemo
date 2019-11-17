package com.mzc.service;

import com.mzc.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 转账
     * @param originalId 源用户Id
     * @param targetId    目标用户Id
     * @param money 转账金额
     */
    void transferById(int originalId,int targetId,long money); /**
     * 转账
     * @param originalName 源用户
     * @param targetName    目标用户
     * @param money 转账金额
     */
    void transfer(String originalName,String targetName,long money);

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
