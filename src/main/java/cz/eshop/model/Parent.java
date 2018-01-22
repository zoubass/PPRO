package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="Parents")
public class Parent {
    private long id;
    private String firstName;
    private String lastName;
    private String emial;
    private Date bornDate;
    private boolean agreement;
    private int tel;

    @Id
    @NotNull
    @NotEmpty
    @Column(name ="id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotEmpty
    @NotNull
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty
    @NotNull
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    @NotEmpty
    @NotNull
    @Column(name = "emial")
    public String getEmial() {
        return emial;
    }

    @Column(name = "bornDate")
    public Date getBornDate() {
        return bornDate;
    }

    @NotEmpty
    @NotNull
    @Column(name = "agreement")
    public boolean isAgreement() {
        return agreement;
    }

    @NotEmpty
    @Column(name = "tel")
    public int getTel() {
        return tel;
    }

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
}
