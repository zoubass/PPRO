package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "parents")
public class Parent {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date bornDate;
    private boolean agreement;
    private int tel;

    //region getters
    @NotNull
    @Column(name = "parent_id", nullable = false)
    public long getId() {
        return id;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
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

    @NotNull
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

    public void setEmail(String email) {
        this.email = email;
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
    //endregion
}
