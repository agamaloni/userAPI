package com.example.userAPIbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @NonNull
    @NotBlank(message = "Phone number cannot be blank")
    @Column(name = "firstName" ,nullable = false)
    private String firstName;


    @NonNull
    @NotBlank(message = "Phone number cannot be blank")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NonNull
    @Email(message = "Email not valid")
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

}
