/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import javax.annotation.PostConstruct;

/**
 *
 * @author POKA
 */
public class QuestionBean extends Bean {

    private QuestionBean question;

    /**
     * Creates a new instance of Question
     */
    public QuestionBean() {
    }

    public QuestionBean getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }  
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            
        } catch (Exception ex) { }
        
    }
}