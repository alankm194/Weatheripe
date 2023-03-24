package com.techreturners.weatheripe.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    @NotBlank
    @Size(max = 20)
    String userName;

    @Column
    @NotBlank
    @Size(max = 200)
    String password;

    @Column
    @Size(max = 50)
    @Email
    String email;

    @Column
    Boolean isActive;

    @Column
    Date createdTimestamp;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PermissionRole role;

}
