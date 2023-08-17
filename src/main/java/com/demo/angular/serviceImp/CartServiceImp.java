package com.demo.angular.serviceImp;

import com.demo.angular.service.CartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CartServiceImp implements CartService {

    @Autowired
    private AccountServiceImp accountServiceImp;

}

