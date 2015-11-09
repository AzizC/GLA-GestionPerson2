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
    
    private Long id;
    private String fistname;
    private Person toFind;
    
    @EJB
    private PersonManager pm;

     /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
       
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public Person getToFind() {
        return toFind;
    }

    public void setToFind(Person toFind) {
        this.toFind = toFind;
    }

    public void searchId(){
        toFind = pm.find(id);
    }
    
     public void searchFirstname(){
        toFind = pm.find(fistname);
    }
    
    public void update(){
        toFind = pm.update(toFind);
    }
    
    public void remove(){
        pm.remove(toFind);
    }
    
    public void undo(){
        pm.refresh(toFind);
    }
}
