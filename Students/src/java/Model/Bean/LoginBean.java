/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.*;
import Model.DAO.*;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


public class LoginBean extends Bean implements Serializable {
    
    private String compte, mot_de_passe;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    @Override
    public void initialisation() {
        
        try {
            
        } catch (Exception ex) { }
        
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    
//    public void authentification() {
//        
//        // -- Définition de la clé de page -- //
//        String cle_page = "";
//        // -- Réccupération du context -- //
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        try {
//                    
//            Etudiant etudiant = new EtudiantDAO().Objet(compte, mot_de_passe);
//            
//            // -- Vérifier l'authentification de l'utilisateur -- //
//            if (etudiant == null) {
//                // -- Notifier l'echec de l'authentification -- //
//                context.addMessage(null, new FacesMessage("Echec", "Authentification refusée!"));
//            } 
//            else {
//                // Définition de la clé de la page à rediriger -- //
//                cle_page = "acceuil";
//                // -- Réccupération de la session -- //
//                HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//                //session.setAttribute("client", c);
//                session.setAttribute("utilisateur", (Utilisateur)etudiant);
//                // -- Definition du titre de la page dans la session -- //
//                context.getExternalContext().getSessionMap().put("titre", "Acceuil");
//                // -- Redirection vers la page d'application -- //
//                context.getExternalContext().redirect("etudiant.xhtml");
//            }
//
//            // -- Définition du rendu de la réponse -- //
//            context.renderResponse();
//            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        //return cle_page;
//    }
    
    public void authentification() {
        
//        String cle = "";
        
        afficherMessage("Message", "Description");
        
//        try {
//            
//            Etudiant etudiant = new EtudiantDAO().Objet(compte, mot_de_passe);
//            
//            // -- Vérifier l'authentification de l'utilisateur -- //
//            if (etudiant != null) {
//            
//                FacesContext facesContext = FacesContext.getCurrentInstance();
//                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//                session.setAttribute("client", (Utilisateur)etudiant);
//            
//                cle = "authentificationEtudiant";
//            }
//            else 
//            {
//                FacesContext fc = FacesContext.getCurrentInstance();
//            
//                FacesMessage fm = new FacesMessage();
//            
//                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
//                fm.setSummary("Mauvais email et/ou password");
//                fm.setDetail("Encoder une valeur correcte pour l'E-mail et/ou Password");
//            
//                fc.addMessage("form:valider", fm);
//            
//                fc.renderResponse();
//            }
//
//            return cle;
//            
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
        
//        return cle;
    }
    

}
