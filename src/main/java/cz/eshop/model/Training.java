package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trainings")
public class Training {
    private long id;
    private String title;
    private Date beginning;
    private Date end;
    private TrainingLevel trainingLevel;
    private List<User> coaches;
    private String note;

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

    @NotNull
    @NotEmpty
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @NotNull
    @NotEmpty
    @Column(name = "beginning")
    public Date getBeginning() {
        return beginning;
    }

    @NotNull
    @NotEmpty
    @Column(name = "end")
    public Date getEnd() {
        return end;
    }

    @NotNull
    @NotEmpty
    @Column(name = "level")
    public TrainingLevel getTrainingLevel() {
        return trainingLevel;
    }

    @NotNull
    @NotEmpty
    @Column(name = "coaches")
    public List<User> getCoaches() {
        return coaches;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }
}
