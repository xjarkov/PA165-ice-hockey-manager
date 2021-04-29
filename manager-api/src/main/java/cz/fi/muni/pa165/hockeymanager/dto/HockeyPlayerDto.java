package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Matus Jarkovic 456441
 */
public class HockeyPlayerDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private Integer offensiveStrength;

    @Getter
    @Setter
    private Integer deffensiveStrength;

    @Getter
    @Setter
    private TeamDto team;

    public String getFullName() {
        return this.firstName + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HockeyPlayerDto that = (HockeyPlayerDto) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                offensiveStrength.equals(that.offensiveStrength) &&
                deffensiveStrength.equals(that.deffensiveStrength) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, offensiveStrength, deffensiveStrength, team);
    }
}
