/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import business.PersonManager;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import persistence.Address;
import persistence.Person;
import persistence.Status;

/**
 *
 * @author chafi4u
 */
@ManagedBean
@RequestScoped
public class RegistrationBean {

    private Person person;
    private Status status;
    private Address address1, address2;
    
    @EJB
    private PersonManager pm;
    
    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean() {
        person = new Person();
        status = new Status();
        address1 = new Address();
        address2 = new Address();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress1() {
        return address1;
    }

    public void setAddress1(Address address1) {
        this.address1 = address1;
    }

    public Address getAddress2() {
        return address2;
    }

    public void setAddress2(Address address2) {
        this.address2 = address2;
    }
    
    public String registerUser(){
        ArrayList<Address> ala = new ArrayList();
        ala.add(address1);
        ala.add(address2);
        pm.register(person, status, ala);
        
        return "/search.xhtml";
    }
}
