package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Kristian Kosorin (456620)
 */
@Data
public class MatchDto {

    private Long id;

    private TeamDto homeTeam;

    private TeamDto visitingTeam;

    private Integer homeTeamScore;

    private Integer visitingTeamScore;

    private Long dateTime;

    public MatchDto() {}

    public MatchDto(TeamDto homeTeam, TeamDto visitingTeam, Long dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.dateTime = dateTime;
    }

    public MatchDto(TeamDto homeTeam, TeamDto visitingTeam, Integer homeTeamScore, Integer visitingTeamScore, Long dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.homeTeamScore = homeTeamScore;
        this.visitingTeamScore = visitingTeamScore;
        this.dateTime = dateTime;
    }

    public String dateFormatted() {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());
        Instant dateTimeInst = Instant.ofEpochSecond(dateTime);
        return DATE_TIME_FORMATTER.format(dateTimeInst);
    }

    @Override
    public String toString() {
        return "MatchDto{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", visitingTeam=" + visitingTeam +
                ", homeTeamScore=" + homeTeamScore +
                ", visitingTeamScore=" + visitingTeamScore +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDto matchDto = (MatchDto) o;
        return homeTeam.equals(matchDto.homeTeam) && visitingTeam.equals(matchDto.visitingTeam) && dateTime.equals(matchDto.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, visitingTeam, homeTeamScore, visitingTeamScore, dateTime);
    }
}
