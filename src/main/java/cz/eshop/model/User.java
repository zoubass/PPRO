package cz.eshop.model;

import cz.eshop.model.Types.BeltTypes;
import cz.eshop.model.Types.RoleTypes;
import cz.eshop.model.Types.StripeTypes;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Size(min = 3, max = 20)
	private String username;
	@NotNull
	@Size(min = 3, max = 50)
	private String firstName;
	@NotNull
	@Size(min = 3, max = 50)
	private String lastName;
	@NotNull
	@Size(min = 3, max = 50)
	private String email;

	private BeltTypes belt;
	private int stripes;
	@NotNull
	@Size(min = 5, max = 20)
	private String password;
	@NotNull
	private RoleTypes role;
	@NotNull
	private int tel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bornDate;
	private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @OneToOne
    @JoinColumn(name = "reminder_id")
    private Reminder reminder;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public User(){
        this.belt = BeltTypes.WHITE;
        this.stripes = StripeTypes.ZERO.Number();
    }

    //region getters
    @NotNull

    @Column(name = "user_id", nullable = false)
    public long getId() {
        return id;
    }

    @Column(name = "parent_id")
    public Parent getParent() {
        return parent;
    }

    @Column(name = "reminder_id")
    public Reminder getReminder() {
        return reminder;
    }

    @Column(name = "ticket_id")
    public Ticket getTicket() {
        return ticket;
    }

    @NotNull
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }


    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @NotNull
    @Column(name = "enabled")
    public boolean getEnabled() {
        return enabled;
    }


    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }


    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "tel")
    public int getTel() {
        return tel;
    }

    @NotNull
    @Column(name = "bornDate")
    public Date getBornDate() {
        return bornDate;
    }

	@Enumerated(EnumType.STRING)
	@Column(name = "belt")
	public BeltTypes getBelt() {
		return belt;
	}

    @Column(name = "stripes")
    public int getStripes() {
        return stripes;
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "role")
    public RoleTypes getRole() {
        return role;
    }
    //endregion

    //region setters
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

    public void setBelt(BeltTypes belt) {
        this.belt = belt;
    }

    public void setStripes(int stripes) {
        this.stripes = stripes;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setRole(RoleTypes role) {
        this.role = role;
    }

	public void setId(long id) {
		this.id = id;
	}

	//endregion
}
