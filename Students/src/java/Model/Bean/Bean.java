/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import Model.BO.Utilisateur;
import java.sql.Connection;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


public abstract class Bean {
    
    protected Connection con;
    protected Utilisateur utilisateur;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    @PostConstruct
    public abstract void initialisation();
    
    public void afficherMessage(String titre, String description) {
        
        FacesContext
            .getCurrentInstance()
            .addMessage(
                null, 
                new FacesMessage(
                    FacesMessage.SEVERITY_INFO, 
                        titre,
                        description
                )
            );
        
    }
    
    public void afficherFermerModal(boolean afficher) {
        
        // -- Fermer le modal -- //
        RequestContext.getCurrentInstance().execute(afficher ? "PF('modal').show();" 
                                                             : "PF('modal').hide();");
        
    }
}
