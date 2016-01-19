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

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "p_id")
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
