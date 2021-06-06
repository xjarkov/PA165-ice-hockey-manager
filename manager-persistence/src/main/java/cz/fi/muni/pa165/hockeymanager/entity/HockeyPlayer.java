package cz.fi.muni.pa165.hockeymanager.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
* @author Kristian Kosorin (456620)
*/
@Entity
@Data
public class HockeyPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer offensiveStrength;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer defensiveStrength;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    public HockeyPlayer() {}

    public HockeyPlayer(String firstName, String lastName, Integer offStrength, Integer defStrength) {
        this(firstName, lastName, offStrength, defStrength, null);
    }

    public HockeyPlayer(String firstName, String lastName, Integer offStrength, Integer defStrength, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offensiveStrength = offStrength;
        this.defensiveStrength = defStrength;
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
