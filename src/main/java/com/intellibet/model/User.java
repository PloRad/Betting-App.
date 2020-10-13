package com.intellibet.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Gender gender;

    private String address;
    private String postCode;
    private String city;
    private String mobileNumber;
    private String password;

    private Double balance;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;




}
