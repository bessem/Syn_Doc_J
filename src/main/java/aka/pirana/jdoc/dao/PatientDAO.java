/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka.pirana.jdoc.dao;

import aka.pirana.jdoc.model.Patient;

/**
 *
 * @author aka
 * CRUD Implementation 4 Patient.class
 */
public class PatientDAO extends AbstractDAO<Patient>{
    
    public PatientDAO() {
        super(Patient.class);
    }
    
}
