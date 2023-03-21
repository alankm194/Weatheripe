package com.techreturners.weatheripe.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietType {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    String dietTypeLabel;

}
