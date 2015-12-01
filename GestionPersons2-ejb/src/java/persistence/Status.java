/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author chafi4u
 */
@Entity
@NamedQueries
({
    @NamedQuery(name="Status.findAll",
        query="SELECT s FROM Status s"),
    @NamedQuery(name="Status.findByTitle", 
    query="SELECT s FROM Status s WHERE LOWER(s.title) LIKE LOWER(?1)"),
})
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;
    private String fullTitle;

    @OneToMany(mappedBy = "status")
    private List<Person> persons = new ArrayList<>();
    
    public Status(){
        
    }
    
    public Status(String title, String fullTitle) {
        this.title = title;
        this.fullTitle = fullTitle;
    }

    public List<Person> getPersons(){
        return persons;
    }
    
    public void addPerson(Person p){
        this.persons.add(p);
    }
    
    public void removePerson(Person p) {
         this.persons.remove(p);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.fullTitle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Status other = (Status) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.fullTitle, other.fullTitle)) {
            return false;
        }
        return true;
    } 
}
