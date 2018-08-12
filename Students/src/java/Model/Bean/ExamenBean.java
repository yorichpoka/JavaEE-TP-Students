/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.Examen;
import javax.annotation.PostConstruct;

/**
 *
 * @author POKA
 */
public class ExamenBean extends Bean {

    private Examen examen;
    
    /**
     * Creates a new instance of ExamenBean
     */
    public ExamenBean() {
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            
        } catch (Exception ex) { }
        
    }
    
}
