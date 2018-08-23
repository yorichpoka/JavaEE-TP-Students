/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BO;

import Model.DAO.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author POKA
 */
public class ChoixReponse extends BO implements Serializable {
    
    public long id_question;
    public Question question;
    public Boolean bonne_reponse;

    public ChoixReponse() {
    }
    
    public ChoixReponse(long id_question) {
        this.id_question = id_question;
    }

    public String bonnereponse() {
        return this.bonne_reponse ? "Oui" : "Non";
    }

    public Boolean getBonnereponse() {
        return bonne_reponse;
    }

    public void setBonnereponse(Boolean bonne_reponse) {
        this.bonne_reponse = bonne_reponse;
    }

    public ChoixReponse(Boolean bonne_reponse) {
        this.bonne_reponse = bonne_reponse;
    }

    public long getIdquestion() {
        return id_question;
    }

    public void setIdquestion(long idquestion) {
        this.id_question = idquestion;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
//     @Override
//    public void creerId() {
//        long id = 0;
//        for(ChoixReponse val : Program.db.choix_reponse)
//        {
//            id = (id < val.getId()) ? val.getId() 
//                                    : id;
//        }
//        this.setId(id);
//    }

    @Override
    public boolean equals(Object obj) {
        return 
            obj != null && ((ChoixReponse)obj).getId() == this.getId();
    }  
    
    public String toJSONString(ChoixReponse obj) {
        
        try {
            return 
                new ObjectMapper().writeValueAsString(obj);            
        } catch (IOException ex) {
            Logger.getLogger(ChoixReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "ChoixReponse {" + "id_question=" + id_question + ", question=" + question + ", bonne_reponse=" + bonne_reponse + ", " + super.toString() + "}";
    }
    
    public Question question() {
        
        // -- Afficher le nombre de question de l'examen -- //
        return new QuestionDAO().Objet(this.id_question);
        
    }
    
    
}
