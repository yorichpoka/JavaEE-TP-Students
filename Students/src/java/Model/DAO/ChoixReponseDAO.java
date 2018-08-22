/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.BO.ChoixReponse;
import Model.Static.STClass;
import Model.Test.MysqlConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author POKA
 */
public class ChoixReponseDAO implements DAO<ChoixReponse>{

    private String table = "choixreponse";
    
    @Override
    public void Ajouter(ChoixReponse obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "INSERT INTO " + table + " " +
                                        "(code, libelle, id_question, bonne_reponse)" + 
                                        "values (" +
                                            STClass.stringParameter(obj.getCode()) + "," + 
                                            STClass.stringParameter(obj.getLibelle()) + "," + 
                                            obj.getIdquestion() + "," + 
                                            (obj.getBonnereponse() ? 1 : 0) + 
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
    public void Modifier(ChoixReponse obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "UPDATE " + table + " " +
                                        "SET " +
                                            "code=" + STClass.stringParameter(obj.getCode()) + "," + 
                                            "libelle=" + STClass.stringParameter(obj.getLibelle()) + "," + 
                                            "id_question=" + obj.getIdquestion() + "," + 
                                            "bonne_reponse=" + (obj.getBonnereponse() ? 1 : 0) + 
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
    public ArrayList<ChoixReponse> Lister() {
        
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                ChoixReponse obj = new ChoixReponse();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setIdquestion(resultats.getInt("id_question"));
                obj.setBonnereponse(resultats.getBoolean("bonne_reponse"));
                
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
    public ChoixReponse Objet(long id) {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + " WHERE id=" + id
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                ChoixReponse obj = new ChoixReponse();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setIdquestion(resultats.getInt("id_question"));
                obj.setBonnereponse(resultats.getBoolean("bonne_reponse"));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (Liste.size() != 0) ? (ChoixReponse)Liste.get(0) : null;
    }

    public ArrayList<ChoixReponse> Lister(long id_question) {
        
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + " WHERE id_question=" + id_question
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                ChoixReponse obj = new ChoixReponse();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setIdquestion(resultats.getInt("id_question"));
                obj.setBonnereponse(resultats.getBoolean("bonne_reponse"));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Liste;
        
    }
    
    public int nombreChoixReponse(long id_question) {
        int nombre = 0;
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT COUNT(*) FROM choixreponse WHERE id_question=" + id_question
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            if (resultats.next()) {                
                nombre = resultats.getInt(1);
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return nombre;
    }
}
