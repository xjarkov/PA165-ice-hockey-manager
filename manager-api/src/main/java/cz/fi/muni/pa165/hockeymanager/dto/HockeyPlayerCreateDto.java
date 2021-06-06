package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class HockeyPlayerCreateDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer offensiveStrength;

    @NotNull
    private Integer defensiveStrength;

    private TeamDto team = null;

    public HockeyPlayerCreateDto() {
    }

    public HockeyPlayerCreateDto(String firstName, String lastName, Integer offensiveStrength, Integer defensiveStrength) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offensiveStrength = offensiveStrength;
        this.defensiveStrength = defensiveStrength;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HockeyPlayerCreateDto that = (HockeyPlayerCreateDto) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && offensiveStrength.equals(that.offensiveStrength) && defensiveStrength.equals(that.defensiveStrength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, offensiveStrength, defensiveStrength);
    }
}
