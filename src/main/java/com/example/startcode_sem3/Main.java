package com.example.startcode_sem3;

import dtos.PersonDTO;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

public class Main {
    static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    public static void main(String[] args) {
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
//        System.out.println(pf.getAllPersons());
        for (PersonDTO p : pf.getAllPersons().getAll()) {
            System.out.println(p.toString());
        }
//        System.out.println(pf.getPerson(2).toString());

    }
}
