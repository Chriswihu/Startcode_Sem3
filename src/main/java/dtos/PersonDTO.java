package dtos;

import entities.Person;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private long id;
    private String fName;
    private String lName;
    private String phone;

    public PersonDTO(Person p) {
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }

    public PersonDTO() {

    }

    public static List<PersonDTO> getDtos(List<Person> rms) {
        List<PersonDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new PersonDTO(rm)));
        return rmdtos;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "id = " + id + ", fName = " + fName + ", lName = " + lName + ", phone = " + phone + '}';
    }

}
