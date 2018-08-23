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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author POKA
 */
public class Etudiant_QuestionBean extends Bean {

    private long id_etudiant;
    private long id_examen;
    private long id_reponse_temp;
    private ArrayList<SelectItem> itemEtudiants;
    private ArrayList<SelectItem> itemExamens;
    private EtudiantDAO etudiantDAO;
    private ExamenDAO examenDAO;
    private ArrayList<Question> questionnaire;
    private boolean passerQuestion;
    private ArrayList<Etudiant_Question> reponseQuestionnaire;
    private ArrayList<Long> id_fifo;
    
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
            this.id_etudiant = 0;
            this.id_examen = 0;
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

    public long getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(long id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public long getId_examen() {
        return id_examen;
    }

    public void setId_examen(long id_examen) {
        this.id_examen = id_examen;
    }

    public ArrayList<Etudiant_Question> getReponseQuestionnaire() {
        return reponseQuestionnaire;
    }

    public void setReponseQuestionnaire(ArrayList<Etudiant_Question> reponseQuestionnaire) {
        this.reponseQuestionnaire = reponseQuestionnaire;
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

    public long getId_reponse_temp() {
        return id_reponse_temp;
    }

    public void setId_reponse_temp(long id_reponse_temp) {
        this.id_reponse_temp = id_reponse_temp;
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
            this.questionnaire = new QuestionDAO().Lister(this.id_examen);
            // -- Mise à jour des réponse -- //
            this.reponseQuestionnaire = new ArrayList<Etudiant_Question>();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void enregistrerReponse(){
        
        try {
            // -- Enregistrement de la réponse -- //
            this.reponseQuestionnaire.add(
                new Etudiant_Question(this.id_etudiant, this.id_examen, 0)
            );
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
    
    public String questionChange(FlowEvent  event) {

        // -- Check si l'element existe dans la pile -- //
//      if (this.id_reponse_temp != 0)
//      {
        // -- Réccupérer l'identifiant de la question en cours -- 'Tap1' //
        long idQuestion = Long.parseLong(event.getOldStep().substring(3));
        // -- Si la question existe déjà -- //
        boolean question_existe = false;
        for (Etudiant_Question val : this.reponseQuestionnaire) {
            if (val.id_question == idQuestion) {
                // -- Définition de l'existence -- //
                question_existe = true;
                // -- Mise à jour de la réponse -- //
                val.id_choix_reponse = this.id_reponse_temp;
                val.choix_reponse = new ChoixReponseDAO().Objet(this.id_reponse_temp);
                // -- Quitter -- //
                break;
            }
        }

        // -- Si la question n'existe pas l'ajouter -- // 
        if (!question_existe) {
            // -- Creation de la reponse -- //
            Etudiant_Question val = new Etudiant_Question(this.id_etudiant, idQuestion, this.id_reponse_temp);
            // -- Mise à jour des references -- //
            val.setQuestion(new QuestionDAO().Objet(idQuestion));
            val.setChoix_reponse(new ChoixReponseDAO().Objet(this.id_reponse_temp));
            // -- Ajout à la liste -- //
            this.reponseQuestionnaire.add(val);
        }

        // -- Si ce n'est pas la dernière étape -- //
        if (event.getNewStep().substring(3) == "0") {
            // -- Mettre à jour les résultat -- //
            RequestContext.getCurrentInstance().update("form:panelwizardResult");
        }
//      }

        return event.getNewStep();
    }
    
    public void enregistrer() {
        
        try {
            // -- Enregistrement des réponses dans la base de données -- //
            for(Etudiant_Question val : this.reponseQuestionnaire)
            {
                // -- Ne pas traiter les réponses null *-- //
                if (val.choix_reponse != null && val.choix_reponse.getId() != 0)
                {
                    new Etudiant_QuestionDAO().Ajouter(val);
                }
            }
            // -- Réinitialisation des valeurs -- //
            this.questionnaire = new ArrayList<Question>();
            this.reponseQuestionnaire = new ArrayList<Etudiant_Question>();
            // -- Mettre à jour les résultat -- //
            //RequestContext.getCurrentInstance().update("form:panelwizard");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}
