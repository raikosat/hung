package com.demo.angular.controller;


import com.demo.angular.exception.ResourceNotFoundException;
import com.demo.angular.model.Account;
import com.demo.angular.model.Product;
import com.demo.angular.model.Role;
import com.demo.angular.repository.AccountRepository;
import com.demo.angular.serviceImp.AccountServiceImp;
import com.demo.angular.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/")
@CrossOrigin("http://localhost:4200/")
public class AccountController {

    @Autowired
    private AccountServiceImp accountServiceImp;
    @Autowired
    private AccountRepository accountRepository;



    //get all products
    @GetMapping("/accounts")
    public ResponseEntity<Page<Account>> getAccountsList(@RequestParam(name = "name", defaultValue = "") String name,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<Account> accounts = accountServiceImp.getAccounts(name,page, size);
        return ResponseEntity.ok(accounts);

    }

    //create product
    @PostMapping("/accounts")
    public Account CreateAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    //get product by ID
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        Account account= accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with id: "+id));
        return ResponseEntity.ok(account);

    }

    //update product by ID
    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccountById(@PathVariable Long id,@RequestBody Account accountDetails ) {
        accountDetails.setId(id);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with id: " + id));

        Account accountUpdate = account;
        accountUpdate = accountServiceImp.updateAccount(accountDetails);
        return ResponseEntity.ok(accountUpdate);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> deleteAccountById(@PathVariable Long id){
        Account account= accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with id: "+id));
        accountRepository.delete(account);
        return ResponseEntity.ok(account);

    }
}
