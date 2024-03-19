package com.example.userAPIbackend.entity;

import com.example.userAPIbackend.validator.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "firstName" ,nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NonNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @ValidPassword
    @Column(name = "password")
    private String password;

}
