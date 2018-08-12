/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.BO.Question;
import Model.Static.STClass;
import Model.Test.MysqlConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author POKA
 */
public class QuestionDAO implements DAO<Question>{

    private String table = "questions";

    public QuestionDAO() {
    }
    
    @Override
    public void Ajouter(Question obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "INSERT INTO " + table + " " +
                                        "(code, libelle, id_examen)" + 
                                        "values (" +
                                            STClass.stringParameter(obj.getCode()) + "," + 
                                            STClass.stringParameter(obj.getLibelle()) + "," + 
                                            obj.getIdexamen() +
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
    public void Modifier(Question obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "UPDATE " + table + " " +
                                        "SET " +
                                            "code=" + STClass.stringParameter(obj.getCode()) + "," + 
                                            "libelle=" + STClass.stringParameter(obj.getLibelle()) + "," + 
                                            "id_examen=" + obj.getIdexamen() +
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
    public ArrayList<Question> Lister() {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Question obj = new Question();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setIdexamen(resultats.getInt("id_examen"));
                // -- AJouter l'exament en reference -- //
                obj.setExamen(new ExamenDAO().Objet(obj.getIdexamen()));
                obj.setChoixreponse(new ChoixReponseDAO().Lister(obj.getId()));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Liste;
    }
    
    public ArrayList<Question> Lister(long id_examen) {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + " WHERE id_examen=" + id_examen
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Question obj = new Question();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setIdexamen(resultats.getInt("id_examen"));
                // -- AJouter l'exament en reference -- //
                obj.setExamen(new ExamenDAO().Objet(obj.getIdexamen()));
                obj.setChoixreponse(new ChoixReponseDAO().Lister(obj.getId()));
                
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
    public Question Objet(long id) {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + " WHERE id=" + id
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Question obj = new Question();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setIdexamen(resultats.getInt("id_examen"));
                // -- AJouter l'exament en reference -- //
                obj.setExamen(new ExamenDAO().Objet(obj.getIdexamen()));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (Liste.size() != 0) ? (Question)Liste.get(0) : null;
    }    
}
