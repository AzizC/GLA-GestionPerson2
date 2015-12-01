/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import business.PersonManager;
import java.util.ArrayList;
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
        
        Person p = new Person("Roberto", "Firmino", "15/05/90");
        Status s = new Status("Mr.", "Mister");
        ArrayList<Address> ala = new ArrayList();
        ala.add(new Address("LIV", "Liverpool"));
        ala.add(new Address("MAN", "Manchester"));
        pm.register(p, s, ala);
        
        p = new Person("Lala", "Titi", "01/12/95");
        s = new Status("Ms.", "Miss");
        ala.clear();
        ala.add(new Address("PAR", "Paris"));
        ala.add(new Address("VIE", "Vienne"));
        pm.register(p, s, ala);
        
        p = new Person("Lola", "Zozo", "03/02/85");
        s = new Status("Ms.", "Miss");
        ala.clear();
        ala.add(new Address("PAR", "Paris"));
        ala.add(new Address("LIV", "Liverpool"));
        pm.register(p, s, ala);
    }
}
