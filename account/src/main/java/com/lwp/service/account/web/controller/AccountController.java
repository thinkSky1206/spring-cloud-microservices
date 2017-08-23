package com.lwp.service.account.web.controller;

import com.lwp.service.account.model.Account;
import org.springframework.web.bind.annotation.*;

/**
 * Author: liuwuping
 * Date: 17/8/16
 * Description:
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {





    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id) {
        Account account = new Account();
        account.setId(id);
        account.setName("lwp");
        return account;
    }



}
