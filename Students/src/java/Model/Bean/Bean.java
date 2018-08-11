/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import java.sql.Connection;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class Bean {
    
    protected Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
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
}
