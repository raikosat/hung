package com.demo.angular.serviceImp;

import com.demo.angular.model.Account;
import com.demo.angular.model.Product;
import com.demo.angular.repository.AccountRepository;
import com.demo.angular.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Page<Account> getAccounts(String keyword, int page, int size) {
        return accountRepository.findByKeyword(keyword, PageRequest.of(page, size));
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    // Triển khai các phương thức khác của AccountService (nếu cần)
}
