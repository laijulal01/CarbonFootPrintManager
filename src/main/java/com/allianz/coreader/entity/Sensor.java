package com.allianz.coreader.entity;

import com.allianz.coreader.config.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "sensor")
@EntityListeners(AuditingEntityListener.class)
public class Sensor extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sensor_code",unique = true)
    private String sensorCode;

    @Column(name = "type")
    private String type;

    @JsonManagedReference
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<Co2Concentration> co2Concentrations;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "district_id", referencedColumnName = "area_code")
    private District district;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Co2Concentration> getCo2Concentrations() {
        return co2Concentrations;
    }

    public void setCo2Concentrations(List<Co2Concentration> co2Concentrations) {
        this.co2Concentrations = co2Concentrations;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}

