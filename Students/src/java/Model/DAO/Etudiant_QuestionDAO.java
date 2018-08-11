/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.BO.*;
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
public class Etudiant_QuestionDAO implements DAO<Etudiant_Question>{

    private String table = "etudiant_question";
    
    @Override
    public void Ajouter(Etudiant_Question obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "INSERT INTO " + table + " " +
                                        "(id_question, id_etudiant, id_choix_reponse)" + 
                                        "values (" +
                                            obj.getId_question()+ "," + 
                                            obj.getId_etudiant()+ "," + 
                                            obj.getId_choix_reponse() + 
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
    public void Modifier(Etudiant_Question obj) {
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "UPDATE " + table + " " +
                                        "SET " +
                                            "id_question=" + obj.getId_question() + "," + 
                                            "id_etudiant=" + obj.getId_etudiant() + "," + 
                                            "id_choix_reponse=" + obj.getId_choix_reponse() +
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
    public ArrayList<Etudiant_Question> Lister() {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Etudiant_Question obj = new Etudiant_Question();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setId_choix_reponse(resultats.getInt("id_choix_reponse"));
                obj.setId_etudiant(resultats.getInt("id_etudiant"));
                obj.setId_question(resultats.getInt("id_question"));
                
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
    public Etudiant_Question Objet(long id) {
        ArrayList Liste = new ArrayList();
        
        try {
            
            MysqlConnect mysql_connect = new MysqlConnect();            
            
            // -- COnnecter -- //
            PreparedStatement prep = mysql_connect.connect().prepareStatement(
                                        "SELECT * FROM " + table + " WHERE id=" + id
                                    );
            
            ResultSet resultats = prep.executeQuery();
            
            while (resultats.next()) {
                
                Etudiant_Question obj = new Etudiant_Question();
                
                obj.setId(resultats.getInt("id"));
                obj.setCode(resultats.getString("code"));
                obj.setLibelle(resultats.getString("libelle"));
                obj.setId_choix_reponse(resultats.getInt("id_choix_reponse"));
                obj.setId_etudiant(resultats.getInt("id_etudiant"));
                obj.setId_question(resultats.getInt("id_question"));
                
                Liste.add(obj);
                
            }
            
            // -- Déconnecter -- //
            mysql_connect.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (Liste.size() != 0) ? (Etudiant_Question)Liste.get(0) : null;
    }

    public double Score(long id_etudiant, long id_examen) {
        try {
            
            QuestionDAO questionDAO = new QuestionDAO();
            int repondu = 0;
            int reussi = 0;
            int echec = 0;
            
            for(Etudiant_Question val : Program.db.etudiant_question)
            {
                Question question = questionDAO.Objet(val.id_question);
                
                if (val.getId_etudiant() == id_etudiant && question.id_examen == id_examen)
                {
                    repondu++;
                    if (question.bonneReponse().equals(val.choix_reponse))
                    {
                        reussi++;
                    }
                    else
                    {
                        echec++;
                    }
                }
            }
            
            return reussi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

   
    
}
