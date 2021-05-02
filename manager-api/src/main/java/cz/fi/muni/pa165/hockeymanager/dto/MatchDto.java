package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Kristian Kosorin (456620)
 */
@Data
public class MatchDto {

    private Long id;

    private TeamDto homeTeam;

    private TeamDto visitingTeam;

    private int homeScore;

    private int visitingScore;

    private LocalDateTime dateTime;

    public MatchDto(TeamDto homeTeam, TeamDto visitingTeam, LocalDateTime dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.dateTime = dateTime;
    }

    public MatchDto() {
    }
}
