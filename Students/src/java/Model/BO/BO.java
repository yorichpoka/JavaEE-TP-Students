/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BO;

import java.io.Serializable;

/**
 *
 * @author POKA
 */
public abstract class BO implements Serializable {
    
    private long id;
    private String code;
    private String libelle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
//    public abstract void creerId ();

    @Override
    public String toString() {
        return "BO {" + "id=" + id + ", code=" + code + ", libelle=" + libelle + '}';
    }
    
}
