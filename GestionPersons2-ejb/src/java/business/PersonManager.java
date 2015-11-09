/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import persistence.Address;
import persistence.Person;
import persistence.Status;

/**
 *
 * @author aziz
 */
@Local
public interface PersonManager {
    
    public void register(Person p);
    public Person find(Long id);
    public Person update(Person p);
    public Person find(String firstname);
    public void remove(Person p);
    public void refresh(Person p);
    public List<Person> getAllPersons(); 
    public List<Address> getAllAddresses();
    public List<Status> getAllStatus();
    
}
