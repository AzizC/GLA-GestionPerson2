/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Address;
import persistence.Person;
import persistence.Status;

/**
 *
 * @author chafi4u
 */
@Stateless
public class PersonManagerBean implements PersonManager {

    @PersistenceContext(unitName = "gp2PU")
    private EntityManager em;

    @Override
    public void register(Person p) {
        
        Status status = p.getStatus();
        
        try{
            Query q = em.createNamedQuery("Status.findByTitle");
            q.setParameter(1, status.getTitle());
            Status s = (Status) q.getSingleResult();
            p.setStatus(s);
        } catch(NoResultException e) {
            // if getSingleResult returns 0 result
            em.persist(status);
        }
      
        em.persist(p);
    }

    @Override
    public Person find(Long id) {
        return em.find(Person.class, id);
    }
    
    @Override
    public Person find(String firstname){
        Query q = em.createNamedQuery("Person.findByFirstname");
        q.setParameter(1, firstname);
        return (Person) q.getSingleResult();
    }

    @Override
    public Person update(Person p) {
        return em.merge(p);
    }

    @Override
    public void remove(Person p) {
        em.remove(em.merge(p));
    }

    @Override
    public Person refresh(Person p) {
        Person attached = em.merge(p);
        em.refresh(attached);
        return attached;
    }

    @Override
    public List<Person> getAllPersons() {
       Query q = em.createNamedQuery("Person.findAll");
       return (List<Person>) q.getResultList();
    }

    @Override
    public List<Address> getAllAddresses() {
       Query q = em.createNamedQuery("Address.findAll");
       return (List<Address>) q.getResultList();
    }
    
    @Override
    public List<Status> getAllStatus() {
       Query q = em.createNamedQuery("Status.findAll");
       return (List<Status>) q.getResultList();
    }
}
