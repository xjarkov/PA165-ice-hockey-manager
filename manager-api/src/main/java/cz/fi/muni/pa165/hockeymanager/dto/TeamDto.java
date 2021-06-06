package cz.fi.muni.pa165.hockeymanager.dto;

import cz.fi.muni.pa165.hockeymanager.enums.Championship;

import lombok.Data;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Petr Šopf (506511)
 */
@Data
public class TeamDto {

    private Long id;

    private String name;

    private Championship championship;

    private Long points = 0L;

    private UserDto manager = null;

    private Set<HockeyPlayerDto> hockeyPlayers = new HashSet<>();

    private Set<MatchDto> matches = new HashSet<>();

    public TeamDto() {}

    public TeamDto(String name, Championship championship) {
        this.name = name;
        this.championship = championship;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", championship=" + championship +
                ", points=" + points +
                ", hockeyPlayers=" + hockeyPlayers +
                ", matches=" + matches +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return name.equals(teamDto.name) && championship == teamDto.championship;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, championship);
    }
}
