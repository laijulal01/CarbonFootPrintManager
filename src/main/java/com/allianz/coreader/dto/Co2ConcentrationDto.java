package com.allianz.coreader.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Co2ConcentrationDto {

    @NotBlank(message = "Sensor Code cannot be empty")
    private String sensorCode;

    @NotBlank(message = "Timestamp cannot be empty")
    private String timeStamp;

    @NotBlank(message = "Concentration cannot be empty")
    private String concentration;
    

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }
}
