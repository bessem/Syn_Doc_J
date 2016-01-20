/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka.pirana.jdoc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aka
 */
@Entity
@Table(name = "CONSULTATION")
public class Consultation implements Serializable{

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getC_date() {
        return c_date;
    }

    public void setC_date(Date c_date) {
        this.c_date = c_date;
    }

    public Float getC_weight() {
        return c_weight;
    }

    public void setC_weight(Float c_weight) {
        this.c_weight = c_weight;
    }

    public Float getC_gm() {
        return c_gm;
    }

    public void setC_gm(Float c_gm) {
        this.c_gm = c_gm;
    }

    public Float getC_mm() {
        return c_mm;
    }

    public void setC_mm(Float c_mm) {
        this.c_mm = c_mm;
    }

    public Float getC_hm() {
        return c_hm;
    }

    public void setC_hm(Float c_hm) {
        this.c_hm = c_hm;
    }

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName="id")
    private Patient patient;
    
    @Column(name = "c_date")
    @Temporal(TemporalType.DATE)
    private Date c_date;
    
    @Column(name = "weight")
    private Float c_weight;
    
    @Column(name = "gm")
    private Float c_gm;
    
    @Column(name = "mm")
    private Float c_mm;
    
    @Column(name = "hm")
    private Float c_hm;
}
