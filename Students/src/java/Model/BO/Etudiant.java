/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BO;

import Model.Test.Program;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author POKA
 */
public class Etudiant extends Utilisateur implements Serializable {
    
    public String prenom;
    public String email;
    public LocalDate date_naissance;
    public Boolean est_masculin;
    
    public int age(){
        
        return
            Period.between(this.date_naissance, LocalDate.now()).getYears();
        
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Boolean getEst_masculin() {
        return est_masculin;
    }

    public void setEst_masculin(Boolean est_masculin) {
        this.est_masculin = est_masculin;
    }
    
     @Override
    public void creerId() {
        long id = 0;
        for(Etudiant val : Program.db.etudiants)
        {
            id = (id < val.getId()) ? val.getId() 
                                    : id;
        }
        this.setId(id);
    }

    @Override
    public boolean equals(Object obj) {
        return 
            (((Etudiant)obj).getId() == this.getId() || ((Etudiant)obj).getCode() == this.getCode());
    } 
    
    public String toJSONString(Etudiant obj) {
        
        try {
            return 
                new ObjectMapper().writeValueAsString(obj);            
        } catch (IOException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Etudiant {" + "prenom=" + prenom + ", email=" + email + ", date_naissance=" + date_naissance + ", est_masculin=" + est_masculin + ", " + super.toString() + "}";
    }
}
