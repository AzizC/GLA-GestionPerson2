/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import business.PersonManager;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import persistence.Address;
import persistence.Person;
import persistence.Status;

/**
 *
 * @author aziz
 */
@Singleton
@Startup
public class StartupSingletonBean implements StartupSingleton {

    @EJB
    private PersonManager pm;
    
    @PostConstruct
    private void registerUsers(){
        
        /**
        Person p = new Person("Roberto", "Firmino", "15/05/90");
        p.setStatus(new Status("Mr.", "Mister"));
        p.addAddress(new Address("1000", "Liverpool"));
        p.addAddress(new Address("2000", "Manchester"));
        pm.register(p);
        
        p = new Person("Lala", "Titi", "01/12/95");
        p.setStatus(new Status("Ms.", "Miss"));
        p.addAddress(new Address("0320", "Paris"));
        p.addAddress(new Address("3420", "Vienne"));
        pm.register(p);
        
        p = new Person("Lola", "Zozo", "03/02/85");
        p.setStatus(new Status("Ms.", "Miss"));
        p.addAddress(new Address("0320", "Paris"));
        p.addAddress(new Address("1000", "Liverpool"));
        pm.register(p); **/
        
        Status s = new Status("Ms.", "Miss");
        
        Address addr1 = new Address("1000", "Liverpool");
        Address addr2 = new Address("0320", "Paris");
        
        Person p = new Person("Roberto", "Firmino", "15/05/90");
        p.setStatus(new Status("Mr.", "Mister"));
        p.addAddress(addr1);
        p.addAddress(new Address("2000", "Manchester"));
        pm.register(p);
        
        p = new Person("Lala", "Titi", "01/12/95");
        p.setStatus(s);
        p.addAddress(addr2);
        p.addAddress(new Address("3420", "Vienne"));
        pm.register(p);
        
        p = new Person("Lola", "Zozo", "03/02/85");
        p.setStatus(s);
        p.addAddress(addr2);
        p.addAddress(addr1);
        pm.register(p); 
    }
}
