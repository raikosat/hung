package com.demo.angular.payload.response;

import com.demo.angular.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String username;
    private String phone;
    private List<String> roles;

}
