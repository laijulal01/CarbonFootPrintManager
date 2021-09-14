package com.allianz.coreader.controller;

import com.allianz.coreader.dto.Co2ConcentrationDto;
import com.allianz.coreader.dto.ResponseDTO;
import com.allianz.coreader.service.Co2concentrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.allianz.coreader.util.Constants.SUCCESS;

@RestController
@RequestMapping("api/v1")
public class Co2ConcentrationController {

    @Autowired
    Co2concentrationService co2concentrationService;

    @PostMapping(value = "/co2concentrations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO>  createCo2Concentration(@Valid @RequestBody Co2ConcentrationDto co2ConcentrationDto) {
        Co2ConcentrationDto co2Concentration= co2concentrationService.createCo2Concentration(co2ConcentrationDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "Concentration created successfully").setData(co2Concentration),
                HttpStatus.OK);
    }

    @PutMapping(value = "/co2concentrations/{sensorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO>  updateCo2Concentration(@PathVariable(name = "sensorId") String sensorId,@Valid @RequestBody Co2ConcentrationDto co2ConcentrationDto) {
        Co2ConcentrationDto co2Concentration=co2concentrationService.updateCo2Concentration(sensorId,co2ConcentrationDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "Concentration Updated successfully").setData(co2Concentration),
                HttpStatus.OK);
    }
}
