package com.demo.angular.service;

import com.demo.angular.model.Account;
import com.demo.angular.model.Product;
import org.springframework.data.domain.Page;


public interface AccountService {
    // Định nghĩa các phương thức tương ứng với chức năng quản lý tài khoản
    public Page<Account> getAccounts(String keyword, int page, int size);
    public Account updateAccount(Account account);
}