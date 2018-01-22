package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trainingLevels")
public class TrainingLevel {
    private String level;
    private String description;

    @Id
    @NotEmpty
    @NotNull
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
