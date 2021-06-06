package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Matus Jarkovic 456441
 */
@Data
public class HockeyPlayerDto {
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer offensiveStrength;

    @NotNull
    private Integer defensiveStrength;

    private TeamDto team = null;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public HockeyPlayerDto() {}

    public HockeyPlayerDto(String firstName, String lastName, Integer offensiveStrength, Integer defensiveStrength) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offensiveStrength = offensiveStrength;
        this.defensiveStrength = defensiveStrength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HockeyPlayerDto that = (HockeyPlayerDto) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && offensiveStrength.equals(that.offensiveStrength) && defensiveStrength.equals(that.defensiveStrength) && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, offensiveStrength, defensiveStrength, team);
    }
}
