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
public class HockeyPlayer {

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
    private Integer defensiveStrength;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    @Getter
    @Setter
    private Team team;

    public HockeyPlayer() {}

    public HockeyPlayer(String firstName, String lastName, Integer offStrength, Integer deffStrength) {
        this(firstName, lastName, offStrength, deffStrength, null);
    }

    public HockeyPlayer(String firstName, String lastName, Integer offStrength, Integer deffStrength, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offensiveStrength = offStrength;
        this.defensiveStrength = deffStrength;
        this.team = team;
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HockeyPlayer hockeyPlayer = (HockeyPlayer) o;
        return firstName.equals(hockeyPlayer.firstName) && lastName.equals(hockeyPlayer.lastName) && offensiveStrength.equals(hockeyPlayer.offensiveStrength) && defensiveStrength.equals(hockeyPlayer.defensiveStrength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, offensiveStrength, defensiveStrength);
    }
}
