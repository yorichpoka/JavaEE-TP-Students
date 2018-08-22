/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.*;
import Model.DAO.*;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author POKA
 */
public class Etudiant_QuestionBean extends Bean {

    private Etudiant selectionEtudiant;
    private Examen selectionExamen;
    private ArrayList<SelectItem> itemEtudiants;
    private ArrayList<SelectItem> itemExamens;
    private EtudiantDAO etudiantDAO;
    private ExamenDAO examenDAO;
    private ArrayList<Question> questionnaire;
    private boolean passerQuestion;
    private ArrayList<Etudiant_Question> reponseQuestionnaire;
    private long positionQuestion;
    private long positionReponse;
    
    /**
     * Creates a new instance of Etudiant_QuestionBean
     */
    public Etudiant_QuestionBean() {
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            // -- Initialiser les variables -- //
            this.etudiantDAO = new EtudiantDAO();
            this.examenDAO = new ExamenDAO();
            this.selectionEtudiant = new Etudiant();
            this.selectionExamen = new Examen();
            this.itemExamens = new ArrayList<SelectItem>();
            this.itemEtudiants = new ArrayList<SelectItem>();
            this.examenDAO.Lister().forEach(l -> 
                this.itemExamens.add(
                    new SelectItem(l.getId(), l.getLibelle())
                )
            );
            this.etudiantDAO.Lister().forEach(l -> 
                this.itemEtudiants.add(
                    new SelectItem(l.getId(), l.getLibelle())
                )
            );
            this.questionnaire = new ArrayList<Question>();
            this.reponseQuestionnaire = new ArrayList<Etudiant_Question>();
        } catch (Exception ex) { }
        
    }

    public boolean isPasserQuestion() {
        return passerQuestion;
    }

    public void setPasserQuestion(boolean passerQuestion) {
        this.passerQuestion = passerQuestion;
    }

    public long getPositionQuestion() {
        return positionQuestion;
    }

    public void setPositionQuestion(long positionQuestion) {
        this.positionQuestion = positionQuestion;
    }

    public ArrayList<Etudiant_Question> getReponseQuestionnaire() {
        return reponseQuestionnaire;
    }

    public void setReponseQuestionnaire(ArrayList<Etudiant_Question> reponseQuestionnaire) {
        this.reponseQuestionnaire = reponseQuestionnaire;
    }

    public Etudiant getSelectionEtudiant() {
        return selectionEtudiant;
    }

    public void setSelectionEtudiant(Etudiant selectionEtudiant) {
        this.selectionEtudiant = selectionEtudiant;
    }

    public Examen getSelectionExamen() {
        return selectionExamen;
    }

    public void setSelectionExamen(Examen selectionExamen) {
        this.selectionExamen = selectionExamen;
    }

    public ArrayList<SelectItem> getItemEtudiants() {
        return itemEtudiants;
    }

    public void setItemEtudiants(ArrayList<SelectItem> itemEtudiants) {
        this.itemEtudiants = itemEtudiants;
    }

    public ArrayList<SelectItem> getItemExamens() {
        return itemExamens;
    }

    public void setItemExamens(ArrayList<SelectItem> itemExamens) {
        this.itemExamens = itemExamens;
    }

    public ArrayList<Question> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(ArrayList<Question> questionnaire) {
        this.questionnaire = questionnaire;
    }
    
    public void nouveauEvaluation(){
        try {
            // -- Mise à jour des questions -- //
            this.questionnaire = new QuestionDAO().Lister(this.selectionExamen.getId());
            // -- Initialiser la postion de la question -- //
            this.positionQuestion = this.questionnaire.size() != 0 ? this.questionnaire.get(0).getId()
                                                                   : 0;
            // -- Mise à jour des réponse -- //
            this.reponseQuestionnaire = new ArrayList<Etudiant_Question>();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public long getPositionReponse() {
        return positionReponse;
    }

    public void setPositionReponse(long positionReponse) {
        this.positionReponse = positionReponse;
    }
    
    public void enregistrerReponse(){
        
        try {
            // -- Enregistrement de la réponse -- //
            this.reponseQuestionnaire.add(
                new Etudiant_Question(this.selectionEtudiant.getId(), this.selectionExamen.getId(), this.positionReponse)
            );
            // -- Suppression de la question -- //
            this.questionnaire.remove(0);
            // -- Mise à jour de la position de la réponse -- //
            this.positionQuestion = this.questionnaire.size() != 0 ? this.questionnaire.get(0).getId()
                                                                   : 0;
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<SelectItem> itemReponses(long id_question){
        
        ArrayList<SelectItem> reponses = new ArrayList<SelectItem>();
            
        try {
            // -- Mise à jour des questions -- //
            new ChoixReponseDAO()
                .Lister(id_question).forEach(l -> 
                    reponses.add(
                        new SelectItem(l.getId(), l.getLibelle())
                    )
                );
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return reponses;
    }
    
    public String  questionChange(FlowEvent  event) {
//        if(skip) {
//            skip = false;   //reset in case user goes back
//            return "confirm";
//        }
//        else {
//            return event.getNewStep();
//        }
            
        return event.getNewStep();
    }
    
    public void enregistrer() {
        
//        afficherMessage("Information", "Description");
        
    }
    
}
