/*

MyEntity e = new MyEntity();

// scenario 1
// tran starts
em.persist(e); 
e.setSomeField(someValue); 
// tran ends, and the row for someField is updated in the database

// scenario 2
// tran starts
e = new MyEntity();
em.merge(e);
e.setSomeField(anotherValue); 
// tran ends but the row for someField is not updated in the database
// (you made the changes *after* merging)

// scenario 3
// tran starts
e = new MyEntity();
MyEntity e2 = em.merge(e);
e2.setSomeField(anotherValue); 
// tran ends and the row for someField is updated
// (the changes were made to e2, not e)

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
        Query q = em.createNamedQuery("Status.findByTitle");
        q.setParameter(1, status.getTitle());
      
        try{     
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
        
        Person p;
        
        try {
            Query q = em.createNamedQuery("Person.findByFirstname");
            q.setParameter(1, firstname);
            p = (Person) q.getSingleResult();
        } catch(NoResultException e) {
            p = null;
        }
        
        return p;
    }

    @Override
    public Person update(Person p) {
                                 //, String firstname, String lastname){
        Person managedPers = em.merge(p);
        //managedPers.setFirstname(firstname);
        //managedPers.setLastname(Lastname);
        return managedPers;
    }

    @Override
    public void remove(Person pers) {
        
        Person managedPers = em.merge(pers);
        Status statusPers = pers.getStatus();
        Status managedStatus = em.merge(statusPers);
        
        em.remove(managedPers);
        
        if(statusPers.getPersons().size() == 1){
            
            em.remove(managedStatus);
            
        } else {
            
            statusPers.getPersons().remove(pers);
            em.merge(statusPers);
        }
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
