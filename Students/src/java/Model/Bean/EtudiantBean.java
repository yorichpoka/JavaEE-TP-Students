/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.Etudiant;
import javax.annotation.PostConstruct;


public class EtudiantBean extends Bean {

    private Etudiant etudiant;
    
    /**
     * Creates a new instance of EtudiantBean
     */
    public EtudiantBean() {
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            
        } catch (Exception ex) { }
        
    }
    
}
