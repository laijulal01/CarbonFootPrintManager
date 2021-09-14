package com.allianz.coreader.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityDto {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Statement cannot be empty")
    private String stateName;
    @NotBlank(message = "City Code cannot be empty")
    private String cityCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
