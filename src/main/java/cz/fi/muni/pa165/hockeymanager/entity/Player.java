package cz.fi.muni.pa165.hockeymanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
* @author Kristian Kosorin (456620)
*/
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @Getter
    @Setter
    private String lastName;

    @NotNull
    @Getter
    @Setter
    private Integer offensiveStrength;

    @NotNull
    @Getter
    @Setter
    private Integer deffensiveStrength;

    @ManyToOne
    @Getter
    @Setter
    private Team team;

    public Player() {}

    public Player(String firstName, String lastName, Integer offStrength, Integer deffStrength, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offensiveStrength = offStrength;
        this.deffensiveStrength = deffStrength;
        this.team = team;
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return firstName.equals(player.firstName) && lastName.equals(player.lastName) && offensiveStrength.equals(player.offensiveStrength) && deffensiveStrength.equals(player.deffensiveStrength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, offensiveStrength, deffensiveStrength);
    }
}
