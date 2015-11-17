/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import business.PersonManager;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import persistence.Person;

/**
 *
 * @author chafi4u
 */
@ManagedBean
@ViewScoped
public class SearchBean {
    
    private String firstname;
    private Person toFind;
    
    @EJB
    private PersonManager pm;

     /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
       
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Person getToFind() {
        return toFind;
    }
    
    public void search(){
        toFind = pm.find(firstname);
    }
    
    public void update(){
        pm.update(toFind);
    }
    
    public void remove(){
        pm.remove(toFind);
    }
    
    public void undo(){
        toFind = pm.refresh(this.toFind);
    }
}
