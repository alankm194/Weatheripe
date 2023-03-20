package com.techreturners.weatheripe.model;

import jakarta.persistence.*;

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
    String userName;

    @Column
    String password;

    @Column
    String email;

    @Column
    String role;

    @Column
    Boolean isActive;

    @Column
    Date createdTimestamp;

}
