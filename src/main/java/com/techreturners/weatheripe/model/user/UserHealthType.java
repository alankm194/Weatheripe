package com.techreturners.weatheripe.model.user;

import com.techreturners.weatheripe.model.recipe.HealthType;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHealthType {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_Type", referencedColumnName = "id")
    HealthType healthType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserAccount userId;

}
