package com.demo.angular.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account {


    public Account(String username, String password, String phone, Gender gender,  Date birthday, String address) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="username" ,unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name="phone")
    private String phone;

    @Column(name="gender")
    private Gender gender;

    @Column(name="authorities")
    private List<String> authorities;

    @Column(name="status")
    private boolean isActive;

    @Column(name="isNotLocked")
    private boolean isNotLocked;



    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="birthday")
    private Date birthday;

    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(name = "accounts_roles",
                joinColumns = @JoinColumn(name = "account_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
                )


    private Set<Role> roles;
}

