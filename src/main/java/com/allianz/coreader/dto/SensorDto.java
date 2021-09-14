package com.allianz.coreader.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SensorDto {

    private Long id;

    @NotBlank(message = "Sensor name cannot be empty")
    private String name;

    @NotBlank(message = "Sensor Code cannot be empty")
    private String sensorCode;

    @NotBlank(message = "Sensor type cannot be empty")
    private String type;

    @NotBlank(message = "District Code cannot be empty")
    private String districtCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
