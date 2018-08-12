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
import javax.faces.bean.ManagedProperty;


public class EtudiantBean extends Bean {

    private Etudiant selectionDonnee;
    private ArrayList<Etudiant> listeDonnee;
    private String rechercher_champ, rechercher_valeur;
    private int total;
    
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            // -- Initialiser les variables -- //
            this.listeDonnee = new ArrayList<Etudiant>();
            this.selectionDonnee = new Etudiant();
            this.total = new EtudiantDAO().Lister().size();
        } catch (Exception ex) { }
        
    }
    
    public void rechercher_methode() {
        
        try {
            // -- Rechercher les enregistrements -- //
            this.listeDonnee = (this.rechercher_champ.equals("*")) ? new EtudiantDAO().Lister() 
                                                                   : new EtudiantDAO().Lister(this.rechercher_valeur, this.rechercher_champ);
            // -- Réinitialiser l'element à supprimer -- //
            this.selectionDonnee = new Etudiant();
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
    }
    
}
