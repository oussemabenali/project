/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package first_sprint_crud.services;
import java.util.List;
/**
 *
 * @author ons
 */


interface IService<T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id);
    public List<T> recuperer();
}
