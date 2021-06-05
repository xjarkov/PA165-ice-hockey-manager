package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
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

    @NonNull
    @Min(value = 0, message = "Score cannot be negative")
    private int homeTeamScore;

    @NonNull
    @Min(value = 0, message = "Score cannot be negative")
    private int visitingTeamScore;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTimeDto;

    public MatchCreateDto() {}

    public MatchCreateDto(TeamDto homeTeam, TeamDto visitingTeam, LocalDateTime dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.dateTimeDto = dateTime;
    }

    public MatchCreateDto(TeamDto homeTeam, TeamDto visitingTeam, int homeTeamScore, int visitingTeamScore, LocalDateTime dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.homeTeamScore = homeTeamScore;
        this.visitingTeamScore = visitingTeamScore;
        this.dateTimeDto = dateTime;
    }

    public String dateFormated() {
        return dateTimeDto.getDayOfMonth() + "." + dateTimeDto.getMonth().getValue() + "." + dateTimeDto.getYear() + " " + dateTimeDto.getHour() + ":" + dateTimeDto.getMinute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchCreateDto matchDto = (MatchCreateDto) o;
        return homeTeamScore == matchDto.homeTeamScore && visitingTeamScore == matchDto.visitingTeamScore && homeTeam.equals(matchDto.homeTeam) && visitingTeam.equals(matchDto.visitingTeam) && dateTimeDto.equals(matchDto.dateTimeDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, visitingTeam, homeTeamScore, visitingTeamScore, dateTimeDto);
    }
}
