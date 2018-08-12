///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Model.Test;
//
//import Model.BO.*;
//import Model.DAO.*;
//import Model.Static.STClass;
//import java.util.ArrayList;
//import org.codehaus.jackson.map.ObjectMapper;
//
///**
// *
// * @author POKA
// */
//
//public class BD {
//    
//    //public ArrayList<Etudiant> utilisateurs;
//    public ArrayList<Examen> examens;
//    public ArrayList<Question> questions;
//    public ArrayList<ChoixReponse> choix_reponse;
//    public ArrayList<Etudiant_Question> etudiant_question;
//    public ArrayList<Etudiant> etudiants;    
//    
//    public static void Charger_Base_De_Donnees(String url_json_db){
//        
//        try {
//            
//            Program.db = new ObjectMapper()
//                            .readValue(
//                                STClass.readAllBytesJava7(url_json_db),
//                                BD.class
//                            );
//            
//            for(ChoixReponse val : Program.db.choix_reponse)
//            {
//                val.setIdquestion(val.getIdquestion());
//            }
//            
//            for(Examen val : Program.db.examens)
//            {
//                val.setQuestions(new QuestionDAO().Lister(val.getId()));
//            }
//            
//            for(Question val : Program.db.questions)
//            {
//                val.setChoix_reponse(new ChoixReponseDAO().Lister(val.getId()));
//                val.setExamen(new ExamenDAO().Objet(val.getIdexamen()));
//            }
//            
//            for(Etudiant_Question val : Program.db.etudiant_question)
//            {
//                val.setEtudiant(new EtudiantDAO().Objet(val.getId_etudiant()));
//                val.setQuestion(new QuestionDAO().Objet(val.getId_question()));
//            }
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//}
