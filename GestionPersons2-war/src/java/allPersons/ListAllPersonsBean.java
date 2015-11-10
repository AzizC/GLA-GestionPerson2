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
    
    private List<Person> persons;
    
    public ListAllPersonsBean(){
        persons = pm.getAllPersons();
    }
    
    public List<Person> allPersons(){
        return persons;
    }
    
    public List<Address> allAddresses(){
        return pm.getAllAddresses();
    }
    
    public List<Status> allStatus() {
         return pm.getAllStatus();
    }
    
    public void update(Person i){
        Person toUpdate = persons.get(persons.indexOf(i));
        toUpdate = pm.update(toUpdate);
    }
    
    public void remove(Person i){
        Person toRemove = persons.get(persons.indexOf(i));
        pm.remove(toRemove);
    }
    
    public void undo(Person i){
        Person toUndo = persons.get(persons.indexOf(i));
        toUndo = pm.update(toUndo);
    }
}
