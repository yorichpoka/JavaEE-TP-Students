/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.Etudiant;
import Model.DAO.EtudiantDAO;
import java.util.ArrayList;
import javax.annotation.PostConstruct;


public class EtudiantBean extends Bean {

    private Etudiant selectionDonnee;
    private ArrayList<Etudiant> listeDonnee;
    private String rechercher_champ, rechercher_valeur;
    private EtudiantDAO etudiantDAO;
    
    /**
     * Creates a new instance of EtudiantBean
     */
    public EtudiantBean() {
    }

    public Etudiant getSelectionDonnee() {
        return selectionDonnee;
    }

    public void setSelectionDonnee(Etudiant selectionDonnee) {
        this.selectionDonnee = selectionDonnee;
    }

    public ArrayList<Etudiant> getListeDonnee() {
        return listeDonnee;
    }

    public void setListeDonnee(ArrayList<Etudiant> listeDonnee) {
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
            this.etudiantDAO = new EtudiantDAO();
            this.listeDonnee = this.etudiantDAO.Lister();
            this.selectionDonnee = new Etudiant();
        } catch (Exception ex) { }
        
    }
    
    public void supprimerDonnee() {
        
        try {
            // -- Suppression de l'objet -- //
            this.etudiantDAO.Supprimer(this.selectionDonnee.getId());
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.etudiantDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Etudiant();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
    }
    
    public void nouveauDonnee() {
        
        // -- Réinitialiser l'element à ajouter -- //
        this.selectionDonnee = new Etudiant();
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(true);
        
    }
    
    public void enregistrer() {
        
        try {
            // -- Si l'objet n'a pas de id -- //
            if (this.selectionDonnee.getId() == 0)
            {
                // -- Ajouter de l'objet -- //
                this.etudiantDAO.Ajouter(this.selectionDonnee); 
            }
            else
            {
                // -- Ajouter de l'objet -- //
                this.etudiantDAO.Modifier(this.selectionDonnee);
            }
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = this.etudiantDAO.Lister();
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Etudiant();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        // -- Afficher ou fermer le modal -- //
        afficherFermerModal(false);
        
    }
    
}
