package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

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

    private TeamDto team;

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
}
