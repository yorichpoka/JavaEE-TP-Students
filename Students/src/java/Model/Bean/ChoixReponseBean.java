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
public class ChoixReponseBean extends Bean {

    private ChoixReponse selectionDonnee;
    private ArrayList<ChoixReponse> listeDonnee;
    private String rechercher_champ, rechercher_valeur;
    private ChoixReponseDAO choixReponseDAO;
    private ArrayList<SelectItem> itemQuestion;
    
    /**
     * Creates a new instance of ChoixReponseBean
     */
    public ChoixReponseBean() {
    }

    public ChoixReponse getSelectionDonnee() {
        return selectionDonnee;
    }

    public void setSelectionDonnee(ChoixReponse selectionDonnee) {
        this.selectionDonnee = selectionDonnee;
    }

    public ArrayList<ChoixReponse> getListeDonnee() {
        return listeDonnee;
    }

    public void setListeDonnee(ArrayList<ChoixReponse> listeDonnee) {
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

    public ArrayList<SelectItem> getItemQuestion() {
        return itemQuestion;
    }

    public void setItemQuestion(ArrayList<SelectItem> itemQuestion) {
        this.itemQuestion = itemQuestion;
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            // -- Initialiser les variables -- //
            this.choixReponseDAO = new ChoixReponseDAO();
            this.listeDonnee = this.choixReponseDAO.Lister();
            this.selectionDonnee = new ChoixReponse();
            this.itemQuestion = new ArrayList<SelectItem>();
            new QuestionDAO().Lister().forEach(l -> this.itemQuestion.add(new SelectItem(l.getId(), l.getLibelle())));
        } catch (Exception ex) { }
        
    }
    
    public void supprimerDonnee() {
        
        try {
            // -- Suppression de l'objet -- //
            this.choixReponseDAO.Supprimer(this.selectionDonnee.getId());
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.choixReponseDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new ChoixReponse();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
    }
    
    public void nouveauDonnee() {
        
        // -- Réinitialiser l'element à ajouter -- //
        this.selectionDonnee = new ChoixReponse();
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(true);
        
    }
    
    public void enregistrer() {
        
        try {
            // -- Si l'objet n'a pas de id -- //
            if (this.selectionDonnee.getId() == 0)
            {
                // -- Ajouter de l'objet -- //
                this.choixReponseDAO.Ajouter(this.selectionDonnee); 
            }
            else
            {
                // -- Ajouter de l'objet -- //
                this.choixReponseDAO.Modifier(this.selectionDonnee);
            }
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.choixReponseDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new ChoixReponse();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(false);
        
    }
    
}
