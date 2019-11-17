package com.mzc.controller;

import com.mzc.domain.Account;
import com.mzc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    IAccountService accountService;
    @RequestMapping("/listAllAccount")
    public String listAllAccount(Model m){
        List<Account> allAccount = accountService.findAllAccount();
        m.addAttribute("list",allAccount);
       // allAccount.forEach(x-> System.out.println(x));
        return "success";
    }
    @RequestMapping("/deleteAccount")
    public String deleteAccount(@RequestParam(value = "id")int id){
        System.out.println("删除id为"+id);
        accountService.deleteAccountById(id);
        return "redirect:listAllAccount";
    }
    @RequestMapping("/editAccount")
    public String editAccount(Model m,@RequestParam(value = "id") int id){

        Account account = accountService.findAccountById(id).get(0);
        m.addAttribute("account",account);
        System.out.println("跳转页面editAccount");
        return "editAccount";
    }
    @RequestMapping("/updateAccount")
    public String updateAccount(Account account){
        System.out.println("更新id为"+account.getId());
        accountService.updateAccount(account);
        return "redirect:listAllAccount";
    }
    @RequestMapping("/addAccount")
    public String addAccount(Account account){
        System.out.println("增加Account"+account.toString());
        accountService.addAccount(account);
        return "redirect:listAllAccount";
    }
    @RequestMapping("/transferAccount")
    public String transferAccount(@RequestParam(value = "originalName") String originalName,
                                  @RequestParam(value = "targetName") String targetName,
                                  @RequestParam(value = "money")long money){
        System.out.println(originalName+"----"+targetName);
        accountService.transfer(originalName,targetName,money);
        return "redirect:listAllAccount";
    }
    @RequestMapping("/transferAccountById")
    public String transferAccountById(@RequestParam(value = "originalName") int originalId,
                                  @RequestParam(value = "targetName") int targetId,
                                  @RequestParam(value = "money")long money){
        System.out.println(originalId+"----"+targetId);
        accountService.transferById(originalId, targetId, money);
        return "redirect:listAllAccount";
    }



}
