package com.techreturners.weatheripe.model.weather;

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
public class Weather {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    double temperatureLow;

    @Column
    double temperatureHigh;

    @Column
    double humidityLow;

    @Column
    double humidityHigh;

    @Column
    double rainIntensityLow;

    @Column
    double rainIntensityHigh;

    @Column
    double snowIntensityLow;

    @Column
    double snowIntensityHigh;

    @Column
    double cloudBaseLow;

    @Column
    double cloudBaseHigh;

    @Column
    double cloudCoverLow;

    @Column
    double cloudCoverHigh;

    @Column
    double dewPointLow;

    @Column
    double dewPointHigh;

}
