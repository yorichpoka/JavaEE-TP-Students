///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Model.Test;
//
//import Model.BO.*;
//import Model.DAO.*;
//import java.io.File;
//import java.time.LocalDate;
//
///**
// *
// * @author POKA
// */
//public class Program {
//    
//    public static BD db = new BD();
//    
//    public static void main(String[] args) {
////        
////        BD.Charger_Base_De_Donnees(
////            new File("./").getAbsolutePath().replace(".", "") + "\\web\\Resources\\db\\base_de_donnees.json"
////        );
////        
//        System.out.println("choix_reponse");
//        db = new BD();
//        Etudiant eee = new EtudiantDAO().Objet("", "aaaa");
//        System.out.println(eee);
//        
////        System.out.println("etudiant_question");
////        db.etudiant_question.forEach(
////            (l) -> System.out.println(l)
////        );
////        
////        System.out.println("etudiants");
////        db.etudiants.forEach(
////            (l) -> System.out.println(l)
////        );
////        
////        System.out.println("examens");
////        db.examens.forEach(
////            (l) -> System.out.println(l)
////        );
////        
////        System.out.println("questions");
////        db.questions.forEach(
////            (l) -> System.out.println(l)
////        );
//        
//        ChoixReponse c = new ChoixReponse();
//        c.setBonnereponse(Boolean.TRUE);
//        c.setCode("aaaaa");
//        c.setLibelle("aaaaa");
//        c.setIdquestion(111);
//        c.setId(3);
//        
//        Etudiant a = new Etudiant();
//        a.setCode("aaaa");
//        a.setLibelle("aaaa");
//        a.setMot_de_passe("aaaa");
//        a.setPrenom("aaaa");
//        a.setEmail("aaaa");
//        a.setDate_naissance(LocalDate.now());
//        a.setEst_masculin(Boolean.TRUE);
//        a.setId(2);
//        
//        Etudiant_Question b = new Etudiant_Question();
//        b.setId_etudiant(1111);
//        b.setId_question(111);
//        b.setId_choix_reponse(111);
//        b.setId(1);
//        
//        Examen d = new Examen();
//        d.setCode("ddddd");
//        d.setLibelle("dddddd");
//        d.setDuree(3000);
//        d.setId(1);
//        
//        Question e = new Question();
//        e.setCode("eeeee");
//        e.setLibelle("eeeee");
//        e.setIdexamen(1111);
//        e.setId(1);
//        
//        System.out.println("Connexion");
//        MysqlConnect mysql_connect = new MysqlConnect();
//        mysql_connect.connect();
//        System.out.println("Connecté");
//        //new ChoixReponseDAO().Ajouter(c);
//        //new ChoixReponseDAO().Modifier(c);
//        //new ChoixReponseDAO().Supprimer(c.getId());
//        //new EtudiantDAO().Ajouter(a);
//        //new EtudiantDAO().Modifier(a);
//        //new Etudiant_QuestionDAO().Ajouter(b);
//        //new Etudiant_QuestionDAO().Modifier(b);
//        //new ExamenDAO().Ajouter(d);
//        //new ExamenDAO().Modifier(d);
//        //new QuestionDAO().Ajouter(e);
//        //new QuestionDAO().Modifier(e);
//        mysql_connect.disconnect();
//        System.out.println("Déconnecté");
//        
//    }
//}
