package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Belt belt;
    private String password;
    private int tel;
    private Date bornDate;
    private boolean enabled;
    private Parent parent;

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    @Size(max=50)
    @Column(name = "password")
    @NotEmpty
    public String getPassword() {
        return password;
    }

    @NotNull
    @Column(name = "enabled")
    public boolean getEnabled() {
        return enabled;
    }

    @NotEmpty
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    @NotEmpty
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Size(max=15)
    @Column(name = "tel")
    public int getTel() {
        return tel;
    }

    @NotNull
    @NotEmpty
    @Column(name = "bornDate")
    public Date getBornDate() {
        return bornDate;
    }

    @Column(name = "belt")
    public Belt getBelt() {
        return belt;
    }

    @Column(name = "parent")
    public Parent getParent() {
        return parent;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public void setBelt(Belt belt) {
        this.belt = belt;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
