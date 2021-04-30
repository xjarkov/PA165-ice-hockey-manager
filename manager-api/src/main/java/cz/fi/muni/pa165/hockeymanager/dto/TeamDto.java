package cz.fi.muni.pa165.hockeymanager.dto;

import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}
