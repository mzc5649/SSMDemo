package com.mzc.dao.impl;

import com.mzc.dao.IAccountDao;
import com.mzc.domain.Account;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository(value = "accountDao")
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    @Qualifier("sqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<Account> findAllAccount() {
        try{
            List<Account> list = sqlSessionTemplate.selectList("com.mzc.dao.IAccountDao.findAllAccount");
            return list;
        }catch(Exception t){

            throw new RuntimeException(t);
        }

    }

    @Override
    public List<Account> findAccountByName(String name) {
        try{
            List<Account> list = sqlSessionTemplate.selectList("com.mzc.dao.IAccountDao.findAccountByName",name);
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
    public List<Account> findAccountById(int id) {
        try{
            List<Account> list = sqlSessionTemplate.selectList("com.mzc.dao.IAccountDao.findAccountById",id);
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
    public void updateAccount(Account account) {
        try{
             sqlSessionTemplate.update("com.mzc.dao.IAccountDao.updateAccount",account);
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public void deleteAccountById(int id) {
        try{
            sqlSessionTemplate.delete("com.mzc.dao.IAccountDao.deleteAccountById",id);
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }

    @Override
    public void addAccount(Account account) {
        try{
            sqlSessionTemplate.insert("com.mzc.dao.IAccountDao.addAccount",account);
        }catch(Exception t){
            throw new RuntimeException(t);
        }
    }
}
