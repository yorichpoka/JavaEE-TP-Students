/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import java.util.ArrayList;

public interface DAO<T>  {
    
    public void Ajouter(T obj);
    public void Modifier(T obj);
    public void Supprimer(long id);
    public ArrayList<T> Lister();
    public T Objet(long id);
    
}
