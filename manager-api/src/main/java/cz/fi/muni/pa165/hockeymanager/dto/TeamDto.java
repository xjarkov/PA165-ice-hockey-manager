package cz.fi.muni.pa165.hockeymanager.dto;

import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Petr Šopf (506511)
 */
public class TeamDto {
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Championship championship;

    @Getter
    @Setter
    private Long points = 0L;

    @Getter
    @Setter
    private UserDto manager = null;

    @Getter
    private Set<HockeyPlayerDto> hockeyPlayers = new HashSet<>();

    @Getter
    private Set<MatchDto> matches = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto team = (TeamDto) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
