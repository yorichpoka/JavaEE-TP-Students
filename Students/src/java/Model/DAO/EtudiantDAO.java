/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.BO.Etudiant;
import Model.Static.STClass;
import Model.Test.MysqlConnect;
import Model.Test.Program;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author POKA
 */
public class EtudiantDAO implements DAO<Etudiant>{

    private String table = "etudiants";
    
    @Override
    public void Ajouter(Etudiant obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "INSERT INTO " + table + " " +
                                        "(code, libelle, mot_de_passe, prenom, email, est_masculin)" + 
                                        "values (" +
                                            STClass.stringParameter(obj.getCode()) + "," + 
                                            STClass.stringParameter(obj.getLibelle()) + "," + 
                                            STClass.stringParameter(obj.getMotdepasse()) + "," + 
                                            STClass.stringParameter(obj.getPrenom()) + "," + 
                                            STClass.stringParameter(obj.getEmail()) + "," + 
                                            (obj.getEst_masculin()? 1 : 0) + 
                                        ")"
                                    );
            
            prep.execute();
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Modifier(Etudiant obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "UPDATE " + table + " " +
                                        "SET " +
                                            "code=" + STClass.stringParameter(obj.getCode()) + "," + 
                                            "libelle=" + STClass.stringParameter(obj.getLibelle()) + "," + 
                                            "mot_de_passe=" + STClass.stringParameter(obj.getMotdepasse()) + "," + 
                                            "prenom=" + STClass.stringParameter(obj.getPrenom()) + "," + 
                                            "email=" + STClass.stringParameter(obj.getEmail()) + "," + 
                                            "est_masculin=" + (obj.getEst_masculin() ? 1 : 0) + 
                                        " WHERE id=" + obj.getId()
                                    );
            
            prep.executeUpdate();
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Supprimer(long id) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "DELETE FROM " + table + " " +
                                        " WHERE id=" + id
                                    );
            
            prep.executeUpdate();
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Etudiant> Lister() {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Etudiant obj = new Etudiant();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setMotdepasse(resultats.getString("mot_de_passe"));
                obj.setPrenom(resultats.getString("prenom"));
                obj.setEmail(resultats.getString("email"));
                obj.setEst_masculin(resultats.getBoolean("est_masculin"));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Liste;
    }

    @Override
    public Etudiant Objet(long id) {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + " WHERE id=" + id
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Etudiant obj = new Etudiant();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setMotdepasse(resultats.getString("mot_de_passe"));
                obj.setPrenom(resultats.getString("prenom"));
                obj.setEmail(resultats.getString("email"));
                obj.setEst_masculin(resultats.getBoolean("est_masculin"));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (Liste.size() != 0) ? (Etudiant)Liste.get(0) : null;
    }
    
    public Etudiant Objet(String compte, String mot_de_passe) {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + 
                                        " WHERE " + 
                                        "code=" + STClass.stringParameter(compte) + " AND " + "mot_de_passe=" + STClass.stringParameter(mot_de_passe)
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Etudiant obj = new Etudiant();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setMotdepasse(resultats.getString("mot_de_passe"));
                obj.setPrenom(resultats.getString("prenom"));
                obj.setEmail(resultats.getString("email"));
                obj.setEst_masculin(resultats.getBoolean("est_masculin"));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (Liste.size() != 0) ? (Etudiant)Liste.get(0) : null;
    }

   
    
}
