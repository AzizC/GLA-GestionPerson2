/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allPersons;

import business.PersonManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import persistence.Address;
import persistence.Person;
import persistence.Status;

/**
 *
 * @author aziz 
 */
@ManagedBean
@ViewScoped
public class ListAllPersonsBean {

    @EJB
    private PersonManager pm;
    
    private List<Person> persons;
    
    @PostConstruct  
    public void init(){
        persons = pm.getAllPersons();
    }  
    
    public void update(Person p){
        pm.update(p);
    }
    
    public void remove(Person p){
        persons.remove(p);
        pm.remove(p);
    }
    
    public void undo(Person p){
        int index = persons.indexOf(p);
        persons.remove(p);
        persons.add(index, pm.refresh(p));
    }
    
    public List<Address> allAddresses(){
        return pm.getAllAddresses();
    }
    
    public List<Status> allStatus() {
         return pm.getAllStatus();
    }

    public List<Person> getPersons() {
        return persons;
    }
    
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
