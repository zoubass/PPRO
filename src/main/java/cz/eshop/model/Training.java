package cz.eshop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
	@Size(min = 1)
    private String title;
    @NotNull
    private Date beginning;
    @NotNull
    private Date ending;
    @NotNull
    private int trainingLevel;
    @NotNull
    private String coach;
    @NotNull
    private int capacity;
    
    private String note;


    //region getters
    @NotNull
    @Column(name ="training_id", nullable = false)
    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @NotNull
    @Column(name = "beginning")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public Date getBeginning() {
        return beginning;
    }

    @NotNull
    @Column(name = "ending")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public Date getEnding() {
        return ending;
    }

    @NotNull
    @Column(name = "level")
    public int getTrainingLevel() {
        return trainingLevel;
    }

    @NotNull
    @Column(name = "coach")
    public String getCoach() {
        return coach;
    }

    @NotNull
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }
    //endregion

    //region setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public void setTrainingLevel(int trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNote(String note) {
        this.note = note;
    }

	public void setId(long id) {
		this.id = id;
	}

	//endregion
}
