/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allPersons;

import business.PersonManager;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import persistence.Address;
import persistence.Person;
import persistence.Status;

/**
 *
 * @author aziz
 */
@ManagedBean
@RequestScoped
public class ListAllPersonsBean {

    @EJB
    private PersonManager pm;
    
    public ListAllPersonsBean(){
        
    }
    
    public List<Person> allPersons(){
        return pm.getAllPersons();
    }
    
    public List<Address> allAddresses(){
        return pm.getAllAddresses();
    }
    
    public List<Status> allStatus() {
         return pm.getAllStatus();
    }
    
    public void update(Person p){
        pm.update(p);
    }
    
    public void remove(Person p){
        pm.remove(p);
    }
    
    public void undo(Person p){
        pm.refresh(p);
    }
}
