package nl.craftsmen.conference;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Conference {


    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate begins;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate ends;
    @NotEmpty
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBegins() {
        return begins;
    }

    public void setBegins(LocalDate begins) {
        this.begins = begins;
    }

    public LocalDate getEnds() {
        return ends;
    }

    public void setEnds(LocalDate ends) {
        this.ends = ends;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
