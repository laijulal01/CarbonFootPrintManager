package com.allianz.coreader.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DistrictDto {

    private Long id;

    @NotBlank(message = "District name cannot be empty")
    private String name;

    @NotBlank(message = "City Code cannot be empty")
    private String cityId;

    @NotBlank(message = "Area Code cannot be empty")
    private String areaCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
