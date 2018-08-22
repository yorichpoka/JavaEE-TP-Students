/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BO;

import Model.DAO.*;
import Model.Static.STClass;
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
public class Examen extends BO implements Serializable {
    
    public ArrayList<Question> questions;
    public int duree;

    public Examen() {
    }

    public Examen(ArrayList<Question> questions, int duree) {
        this.questions = questions;
        this.duree = duree;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    } 
    
//    @Override
//    public void creerId() {
//        long id = 0;
//        for(Examen val : Program.db.examens)
//        {
//            id = (id < val.getId()) ? val.getId() 
//                                    : id;
//        }
//        this.setId(id);
//    }

    @Override
    public boolean equals(Object obj) {
        return 
            (((Examen)obj).getCode() == this.getCode());
    }  
    
    public String toJSONString(Examen obj) {
        
        try {
            return 
                new ObjectMapper().writeValueAsString(obj);            
        } catch (IOException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String toString() {        
        return "Examen {" + "questions=" + ((questions != null) ? questions.size() : "") + ", duree=" + duree + ", " + super.toString() + "}";
    }
    
    public int nombreQuestion() {
        
        // -- Afficher le nombre de question de l'examen -- //
        return new ExamenDAO().nombreQuestion(getId());
        
    }
}
