/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BO;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author POKA
 */
public class Utilisateur extends BO implements Serializable {
    
    public String mot_de_passe;

    public Utilisateur() {
    }

    public Utilisateur(String compte, String nom_utilisateur, String mot_de_passe) {
        this.setCode(compte);
        this.setLibelle(nom_utilisateur);
        this.mot_de_passe = mot_de_passe;
    }    
    
    public String getMotdepasse() {
        return mot_de_passe;
    }

    public void setMotdepasse(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public void creerId() {
//        long id = 0;
//        for(Utilisateur val : Program.db.utilisateurs)
//        {
//            id = (id < val.getId()) ? val.getId() 
//                                    : id;
//        }
//        this.setId(id);
    }

    @Override
    public boolean equals(Object obj) {
        return 
            ((Utilisateur)obj).getId() == this.getId();
    } 
    
    public String toJSONString(Utilisateur obj) {
        
        try {
            return 
                new ObjectMapper().writeValueAsString(obj);            
        } catch (IOException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Utilisateur {" + "mot_de_passe=" + mot_de_passe + ", " + super.toString() + "}";
    }
    
}
