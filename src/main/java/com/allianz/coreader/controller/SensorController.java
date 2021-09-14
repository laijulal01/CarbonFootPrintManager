package com.allianz.coreader.controller;

import com.allianz.coreader.dto.ResponseDTO;
import com.allianz.coreader.dto.SensorDto;
import com.allianz.coreader.entity.Sensor;
import com.allianz.coreader.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.allianz.coreader.util.Constants.SUCCESS;

@RestController
@RequestMapping("api/v1")
public class SensorController {
    
    @Autowired
    SensorService sensorService;

    @PostMapping(value = "/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createSensor(@Valid  @RequestBody SensorDto sensorDto) {
        SensorDto sensor = sensorService.createSensor(sensorDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "Sensor created successfully").setData(sensor),
                HttpStatus.OK);
    }

    @PutMapping(value = "/sensors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> updateSensor(@PathVariable(name = "id") Long id,@Valid @RequestBody SensorDto sensorDto) {
        SensorDto sensor=sensorService.updateSensor(id,sensorDto);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "Sensor updated successfully").setData(sensor),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/sensors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> deleteSensor(@PathVariable(name = "id") Long id) {
        sensorService.deleteSensor(id);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "Sensor deleted successfully").setData(null),
                HttpStatus.OK);
    }

    @GetMapping(value = "/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO> getAllSensors() {
        List<SensorDto> allSensors = sensorService.getAllSensors();
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "All sensors are  retrieved successfully").setData(allSensors),
                HttpStatus.OK);
    }

    @GetMapping(value = "/sensors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDTO>  getSensor(@PathVariable(name = "id") Long sensorId) {
        SensorDto sensor = sensorService.getSensor(sensorId);
        return new ResponseEntity<>(
                new ResponseDTO(SUCCESS, "200", "Sensor retrieved successfully").setData(sensor),
                HttpStatus.OK);
    }
}
