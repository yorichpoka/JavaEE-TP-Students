/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.Examen;
import Model.DAO.ExamenDAO;
import java.util.ArrayList;
import javax.annotation.PostConstruct;

/**
 *
 * @author POKA
 */
public class ExamenBean extends Bean {

    private Examen selectionDonnee;
    private ArrayList<Examen> listeDonnee;
    private String rechercher_champ, rechercher_valeur;
    private ExamenDAO examenDAO;
    
    /**
     * Creates a new instance of ExamenBean
     */
    public ExamenBean() {
    }

    public Examen getSelectionDonnee() {
        return selectionDonnee;
    }

    public void setSelectionDonnee(Examen selectionDonnee) {
        this.selectionDonnee = selectionDonnee;
    }

    public ArrayList<Examen> getListeDonnee() {
        return listeDonnee;
    }

    public void setListeDonnee(ArrayList<Examen> listeDonnee) {
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
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            // -- Initialiser les variables -- //
            this.examenDAO = new ExamenDAO();
            this.listeDonnee = this.examenDAO.Lister();
            this.selectionDonnee = new Examen();
        } catch (Exception ex) { }
        
    }
    
    public void supprimerDonnee() {
        
        try {
            // -- Suppression de l'objet -- //
            this.examenDAO.Supprimer(this.selectionDonnee.getId());
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.examenDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Examen();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
    }
    
    public void nouveauDonnee() {
        
        // -- Réinitialiser l'element à ajouter -- //
        this.selectionDonnee = new Examen();
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(true);
        
    }
    
    public void enregistrer() {
        
        try {
            // -- Si l'objet n'a pas de id -- //
            if (this.selectionDonnee.getId() == 0)
            {
                // -- Ajouter de l'objet -- //
                this.examenDAO.Ajouter(this.selectionDonnee); 
            }
            else
            {
                // -- Ajouter de l'objet -- //
                this.examenDAO.Modifier(this.selectionDonnee);
            }
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.examenDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Examen();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(false);
        
    }
    
}
