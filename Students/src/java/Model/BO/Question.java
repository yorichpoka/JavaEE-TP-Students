/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BO;

import Model.DAO.*;
import Model.Static.STClass;
import Model.Test.Program;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author POKA
 */
public class Question extends BO implements Serializable {
    
    public long id_examen;
    public Examen examen;
    public ArrayList<ChoixReponse> choix_reponse;

    public Question() {
    }

    public Question(ArrayList<ChoixReponse> choix_reponse) {
        this.choix_reponse = choix_reponse;
    }

    public ArrayList<ChoixReponse> getChoixreponse() {
        return choix_reponse;
    }

    public void setChoixreponse(ArrayList<ChoixReponse> choixreponse) {
        this.choix_reponse = choixreponse;
    }

    public long getIdexamen() {
        return id_examen;
    }

    public void setIdexamen(long id_examen) {
        this.id_examen = id_examen;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public void setChoix_reponse(ArrayList<ChoixReponse> choix_reponse) {
        this.choix_reponse = choix_reponse;
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    } 
    
     @Override
    public void creerId() {
        long id = 0;
        for(Question val : Program.db.questions)
        {
            id = (id < val.getId()) ? val.getId() 
                                    : id;
        }
        this.setId(id);
    }

    public ChoixReponse bonneReponse() {
        
        for(ChoixReponse val : Program.db.choix_reponse)
        {
            if (val.bonne_reponse)
            {
                return val;    
            }
        }
        
        return null;
    } 

    @Override
    public boolean equals(Object obj) {
        return 
            ((Question)obj).getId() == this.getId();
    } 
    
    public String toJSONString(Question obj) {
        
        try {
            return 
                new ObjectMapper().writeValueAsString(obj);            
        } catch (IOException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Question {" + "id_examen=" + id_examen + ", examen=" + examen != null ? examen.toString() : "" + ", choix_reponse=" + ((choix_reponse != null) ? choix_reponse.size() : "") + ", " + super.toString() + "}";
    }
}
