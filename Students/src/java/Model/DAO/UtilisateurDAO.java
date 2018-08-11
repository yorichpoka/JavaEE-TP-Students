///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Model.DAO;
//
//import Model.BO.Utilisateur;
//import Model.Test.Program;
//import java.util.ArrayList;
//
///**
// *
// * @author POKA
// */
//public class UtilisateurDAO implements DAO<Utilisateur>{
//
//    @Override
//    public void Ajouter(Utilisateur obj) {
//        try {
//            
//            obj.creerId();
//            Program.db.utilisateurs.add(obj);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void Modifier(Utilisateur obj) {
//        try {
//            
//            for(Utilisateur val : Program.db.utilisateurs)
//            {
//                if (val.getId() == obj.getId())
//                {
//                    val.setCode(obj.getCode());
//                    val.setLibelle(obj.getLibelle());
//                    val.setMotdepasse(obj.getMotdepasse());
//                    
//                    break;
//                }
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void Supprimer(long id) {
//        try {
//            
//            for(Utilisateur val : Program.db.utilisateurs)
//            {
//                if (val.getId() == id)
//                {
//                    Program.db.utilisateurs.remove(val);
//                    break;
//                }
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public ArrayList<Utilisateur> Lister() {
//        try {
//            
//            return
//                Program.db.utilisateurs;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public Utilisateur Objet(long id) {
//        try {
//            
//            for(Utilisateur val : Program.db.utilisateurs)
//            {
//                if (val.getId() == id)
//                {
//                    return
//                        val;
//                }
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return null;
//    }
//
//   
//    
//}
