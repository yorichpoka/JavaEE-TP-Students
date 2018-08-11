/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.BO.Question;
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
public class QuestionDAO implements DAO<Question>{

    private String table = "questions";
    
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
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (Liste.size() != 0) ? (Question)Liste.get(0) : null;
    }

    public ArrayList<Question> Lister(long id_examen) {
        try {
            ArrayList<Question> liste = new ArrayList<Question>();
            for(Question val : Program.db.questions)
            {
                if (val.getIdexamen()== id_examen)
                {
                    liste.add(val);
                }
            }
            return liste;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

   
    
}
