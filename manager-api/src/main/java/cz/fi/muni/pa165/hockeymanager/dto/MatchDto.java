package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Kristian Kosorin (456620)
 */

@Data
@EqualsAndHashCode
public class MatchDto {

    @Getter
    private Long id;

    @Getter
    @Setter
    private TeamDto homeTeam;

    @Getter
    @Setter
    private TeamDto visitingTeam;

    @Getter
    @Setter
    private int homeScore;

    @Getter
    @Setter
    private int visitingScore;

    @Getter
    @Setter
    private LocalDateTime dateTime;
}
