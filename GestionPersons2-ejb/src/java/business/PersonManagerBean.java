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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    public void register(Person p, Status s, List<Address> addresses) {
        
        TypedQuery<Status> query = em.createNamedQuery("Status.findByTitle", Status.class);
        
        try {
            query.setParameter(1, s.getTitle());
            Status sBDD = query.getSingleResult();
            em.persist(p);
            p.setStatus(sBDD);
        } catch(NoResultException e){
            em.persist(s);
            em.persist(p);
            p.setStatus(s);
        }
        
        TypedQuery<Address> query2 = em.createNamedQuery("Address.findByCode", Address.class);
        
        for (Address a : addresses) {
            try {
                query2.setParameter(1, a.getCode());
                Address aBDD = query2.getSingleResult();
                em.persist(p);
                p.addAddress(aBDD);
            } catch(NoResultException e){
                em.persist(a);
                em.persist(p);
                p.addAddress(a);
            }
        }
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
        
        Status status = managedPers.getStatus();
        if(status.getPersons().size() == 1){
            em.remove(status);   
        } else {  
            managedPers.removeStatus();
        }
        
        for(Address a : managedPers.getAddresses()){
            if(a.getPersons().size() == 1){
               em.remove(a);
            } else {
               managedPers.removeAddress(a);
            }
        }
        
        em.remove(managedPers);
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
