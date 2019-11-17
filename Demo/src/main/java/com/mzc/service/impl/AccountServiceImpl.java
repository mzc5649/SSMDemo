package com.mzc.service.impl;

import com.mzc.dao.IAccountDao;
import com.mzc.domain.Account;
import com.mzc.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "accountService")
public class AccountServiceImpl implements IAccountService {
    @Resource(name = "accountDao")
    IAccountDao accountDao;

    @Override
    public void transferById(int originalId, int targetId, long money) {
        Account originalAccount = accountDao.findAccountById(originalId).get(0);
        Account targetAccount = accountDao.findAccountById(targetId).get(0);
        originalAccount.setMoney(originalAccount.getMoney()-money);
        targetAccount.setMoney(targetAccount.getMoney()+money);
        accountDao.updateAccount(originalAccount);

        accountDao.updateAccount(targetAccount);
    }

    @Override
    public void transfer(String originalName, String targetName, long money) {
        Account originalAccount = accountDao.findAccountByName(originalName).get(0);
        Account targetAccount = accountDao.findAccountByName(targetName).get(0);
        originalAccount.setMoney(originalAccount.getMoney()-money);
        targetAccount.setMoney(targetAccount.getMoney()+money);
        accountDao.updateAccount(originalAccount);
        //int i=1/0;
        accountDao.updateAccount(targetAccount);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public List<Account> findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }

    @Override
    public List<Account> findAccountById(int id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccountById(int id) {
        accountDao.deleteAccountById(id);
    }

    @Override
    public void addAccount(Account account) {
        accountDao.addAccount(account);
    }
}
