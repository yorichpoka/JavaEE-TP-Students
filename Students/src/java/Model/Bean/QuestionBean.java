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

/**
 *
 * @author POKA
 */
public class QuestionBean extends Bean {

    private Question selectionDonnee;
    private ArrayList<Question> listeDonnee;
    private String rechercher_champ, rechercher_valeur;
    private QuestionDAO questionDAO;
    private ArrayList<SelectItem> itemExamens;
    
    /**
     * Creates a new instance of QuestionBean
     */
    public QuestionBean() {
    }

    public Question getSelectionDonnee() {
        return selectionDonnee;
    }

    public void setSelectionDonnee(Question selectionDonnee) {
        this.selectionDonnee = selectionDonnee;
    }

    public ArrayList<Question> getListeDonnee() {
        return listeDonnee;
    }

    public void setListeDonnee(ArrayList<Question> listeDonnee) {
        this.listeDonnee = listeDonnee;
    }

    public String getRechercher_champ() {
        return rechercher_champ;
    }

    public void setRechercher_champ(String rechercher_champ) {
        this.rechercher_champ = rechercher_champ;
    }

    public String getRechercher_valeur() {
        return rechercher_valeur;
    }

    public void setRechercher_valeur(String rechercher_valeur) {
        this.rechercher_valeur = rechercher_valeur;
    }

    public ArrayList<SelectItem> getItemExamens() {
        return itemExamens;
    }

    public void setItemExamens(ArrayList<SelectItem> itemExamens) {
        this.itemExamens = itemExamens;
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            // -- Initialiser les variables -- //
            this.questionDAO = new QuestionDAO();
            this.listeDonnee = this.questionDAO.Lister();
            this.selectionDonnee = new Question();
            this.itemExamens = new ArrayList<SelectItem>();
            new ExamenDAO().Lister().forEach(l -> this.itemExamens.add(new SelectItem(l.getId(), l.getLibelle())));
        } catch (Exception ex) { }
        
    }
    
    public void supprimerDonnee() {
        
        try {
            // -- Suppression de l'objet -- //
            this.questionDAO.Supprimer(this.selectionDonnee.getId());
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.questionDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Question();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
    }
    
    public void nouveauDonnee() {
        
        // -- Réinitialiser l'element à ajouter -- //
        this.selectionDonnee = new Question();
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(true);
        
    }
    
    public void enregistrer() {
        
        try {
            // -- Si l'objet n'a pas de id -- //
            if (this.selectionDonnee.getId() == 0)
            {
                // -- Ajouter de l'objet -- //
                this.questionDAO.Ajouter(this.selectionDonnee); 
            }
            else
            {
                // -- Ajouter de l'objet -- //
                this.questionDAO.Modifier(this.selectionDonnee);
            }
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.questionDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Question();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(false);
        
    }
    
}
