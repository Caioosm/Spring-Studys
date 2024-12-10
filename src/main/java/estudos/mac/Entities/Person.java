package estudos.mac.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person_tb")
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name", nullable=false, length=80)
    private String firstName;
    @Column(name = "last_name", nullable=false, length=80)
    private String lastName;
    @Column(nullable=false, length = 100)
    private String address;
    @Column(nullable=false)
    private String gender;

    public Person() {
    }

    public Person(String address, String firstName, String gender, Long id, String lastName) {
        this.address = address;
        this.firstName = firstName;
        this.gender = gender;
        this.id = id;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
