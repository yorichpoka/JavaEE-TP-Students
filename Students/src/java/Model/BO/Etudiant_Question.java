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
public class Etudiant_Question extends BO implements Serializable {
    
    public long id_etudiant;
    public Etudiant etudiant;
    public long id_question;
    public Question question;
    public long id_choix_reponse;
    public ChoixReponse choix_reponse;

    public Etudiant_Question() {
    }
    
    public Etudiant_Question(long id_etudiant, long id_question) {
        this.id_etudiant = id_etudiant;
        this.id_question = id_question;
    }
    
    public Etudiant_Question(long id_etudiant, long id_question, long id_reponse) {
        this.id_etudiant = id_etudiant;
        this.id_question = id_question;
        this.choix_reponse = new ChoixReponse(id_reponse);
    }

    public long getId_etudiant() {
        return id_etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setChoix_reponse(ChoixReponse choix_reponse) {
        this.choix_reponse = choix_reponse;
    }

    public void setId_etudiant(long id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public long getId_question() {
        return id_question;
    }

    public void setId_question(long id_question) {
        this.id_question = id_question;
    }

    public Question getQuestion() {
        return question;
    }

    public ChoixReponse getChoix_reponse() {
        return choix_reponse;
    }

    public long getId_choix_reponse() {
        return id_choix_reponse;
    }

    public void setId_choix_reponse(long id_choix_reponse) {
        this.id_choix_reponse = id_choix_reponse;
    }
    
//     @Override
//    public void creerId() {
//        long id = 0;
//        for(Etudiant_Question val : Program.db.etudiant_question)
//        {
//            id = (id < val.getId()) ? val.getId() 
//                                    : id;
//        }
//        this.setId(id);
//    }

    @Override
    public boolean equals(Object obj) {
        return 
            ((Etudiant_Question)obj).getId() == this.getId();
    } 
    
    public String toJSONString(Etudiant_Question obj) {
        
        try {
            return 
                new ObjectMapper().writeValueAsString(obj);            
        } catch (IOException ex) {
            Logger.getLogger(Etudiant_Question.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Etudiant_Question {" + "id_etudiant=" + id_etudiant + ", etudiant=" + etudiant != null ? etudiant.toString() : "" + ", id_question=" + id_question + ", question=" + question != null ? question.toString() : "" + ", id_choix_reponse=" + id_choix_reponse + ", choix_reponse=" + choix_reponse + ", " + super.toString() + "}";
    }
}
