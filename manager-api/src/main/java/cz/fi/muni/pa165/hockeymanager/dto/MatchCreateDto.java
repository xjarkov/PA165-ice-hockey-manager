package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Kristian Kosorin (456620)
 */
@Data
public class MatchCreateDto {
    @NonNull
    private TeamDto homeTeam;

    @NonNull
    private TeamDto visitingTeam;

    @Nullable
    @Min(value = 0, message = "Score cannot be negative")
    private Integer homeTeamScore;

    @Nullable
    @Min(value = 0, message = "Score cannot be negative")
    private Integer visitingTeamScore;

    private Long dateTime;

    public MatchCreateDto() {}

    public MatchCreateDto(TeamDto homeTeam, TeamDto visitingTeam, Long dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.dateTime = dateTime;
    }

    public MatchCreateDto(TeamDto homeTeam, TeamDto visitingTeam, Integer homeTeamScore, Integer visitingTeamScore, Long dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.homeTeamScore = homeTeamScore;
        this.visitingTeamScore = visitingTeamScore;
        this.dateTime = dateTime;
    }

    public String dateFormated() {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());
        Instant dateTimeInst = Instant.ofEpochSecond(dateTime);
        return DATE_TIME_FORMATTER.format(dateTimeInst);
    }

    @Override
    public String toString() {
        return "MatchCreateDto{" +
                "homeTeam=" + homeTeam +
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
        MatchCreateDto matchDto = (MatchCreateDto) o;
        return homeTeam.equals(matchDto.homeTeam) && visitingTeam.equals(matchDto.visitingTeam) && dateTime.equals(matchDto.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, visitingTeam, homeTeamScore, visitingTeamScore, dateTime);
    }
}
