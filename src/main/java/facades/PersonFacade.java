package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade implements IPersonFacade{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {
    }

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonDTO create(PersonDTO p){
        Person np = new Person(p.getFirstName(), p.getLastName(), p.getPhone());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(np);
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        Person p = new Person(fName, lName, phone);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        return new PersonDTO(p);
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return new PersonsDTO(persons);
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, p.getId());
        try {
            em.getTransaction().begin();
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            person.setPhone(p.getPhone());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
}
