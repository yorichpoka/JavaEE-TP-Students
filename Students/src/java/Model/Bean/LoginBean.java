/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.*;
import Model.DAO.*;
import Model.Static.STClass;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;


public class LoginBean extends Bean implements Serializable {
    
    private String titre_de_la_page, id_page;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            // -- Définition du titre de la page -- //
            this.titre_de_la_page = "Page d'authentification";
            // -- Initialiser l'utilisateur en session -- //
            this.utilisateur = new Utilisateur();
        } catch (Exception ex) { }
        
    }

    public String getTitre_de_la_page() {
        return titre_de_la_page;
    }

    public void setTitre_de_la_page(String titre_de_la_page) {
        this.titre_de_la_page = titre_de_la_page;
    }

    public String getId_page() {
        return id_page;
    }

    public void setId_page(String id_page) {
        this.id_page = id_page;
    }
    
    public String authentification() {
        
        try {
            // -- Vérifier que le paramètres sont soumis -- //
            if (STClass.isNullOrEmpty(this.utilisateur.getCode())){
                throw new Exception("Le compte est requis.");
            }
            else if (STClass.isNullOrEmpty(this.utilisateur.getMotdepasse())){
                throw new Exception("Le mot de passe est requis.");
            }
            
            // -- Réccupérer l'étudiant authentifier -- //
            this.utilisateur = new EtudiantDAO().Objet(this.utilisateur.getCode(), this.utilisateur.getMotdepasse());
            
            // -- Vérifier l'authentification de l'utilisateur -- //
            if (this.utilisateur != null) {
                // -- COntext de la requete -- //
                FacesContext facesContext = FacesContext.getCurrentInstance();
                // -- Réccupération de l'objet session -- //
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                // -- Mise à jour de l'utilisateur dans la session -- //
                session.setAttribute("utilisateur", this.utilisateur);
                // -- Définition du titre de la page -- //
                this.titre_de_la_page = "Page d'acceuil";
                // -- Renvoyer la clé de la requête -- //
                return "authentificationVersAcceuil";
            }
            else 
            {
                // -- Affichier le message d'erreur -- //
                afficherMessage("Compte ou mot de passe incorrect", null);
                // -- Vider les champs -- //
                this.utilisateur = new Etudiant();
                // -- Annuler la redirection -- //
                return "";
            }            
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
        // -- Vider les champs -- //
        this.utilisateur = new Etudiant();
        // -- Annuler la redirection -- //
        return "";
        
    }
    
    public String deconnexion() {
        
        try {
            // -- Vider tous les paramètre du bean -- //
            this.utilisateur = new Etudiant();
            this.titre_de_la_page = null;
            // -- Renvoyer la clé de la requête -- //
            return "acceuilVersAuthentification";           
        }
        catch(Exception ex){
            // -- Affichier le message d'erreur -- //
            afficherMessage(ex.getMessage(), null);
        }
        
        // -- Annuler la redirection -- //
        return "";
        
    }
    
    public void onTabChange(TabChangeEvent event) {
        // -- Affichier le message d'erreur -- //
        afficherMessage("Active Tab: " + event.getTab().getTitle(), null);
    }
}
