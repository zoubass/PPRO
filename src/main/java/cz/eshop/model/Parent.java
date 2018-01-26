package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="parents")
public class Parent {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String emial;
    private Date bornDate;
    private boolean agreement;
    private int tel;

    @OneToMany(mappedBy = "parent")
    private List<User> users;

    //region getters
    @NotNull

    @Column(name ="parent_id", nullable = false)
    public long getId() {
        return id;
    }


    @NotNull
    @Column(name = "user_id")
    public List<User> getUsers() {
        return users;
    }


    @NotNull
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }


    @NotNull
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }


    @NotNull
    @Column(name = "emial")
    public String getEmial() {
        return emial;
    }

    @Column(name = "bornDate")
    public Date getBornDate() {
        return bornDate;
    }


    @NotNull
    @Column(name = "agreement")
    public boolean isAgreement() {
        return agreement;
    }


    @Column(name = "tel")
    public int getTel() {
        return tel;
    }


    //endregion

    //region setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //endregion
}
