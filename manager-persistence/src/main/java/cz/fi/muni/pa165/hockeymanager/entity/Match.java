package cz.fi.muni.pa165.hockeymanager.entity;

import java.time.*;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 * @author Lukas Machalek (485196)
 */
@Entity
@Table(name = "IceHockeyMatch")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    private Team visitingTeam;

    @Nullable
    @Getter
    @Setter
    private Integer homeTeamScore;

    @Nullable
    @Getter
    @Setter
    private Integer visitingTeamScore;

    @NotNull
    @Getter
    @Setter
    private Long dateTime;

    public Match(){}

    public Match(Team homeTeam, Team visitingTeam, Long dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.dateTime = dateTime;

        homeTeamScore = 0;
        visitingTeamScore = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeTeam.equals(match.homeTeam) && visitingTeam.equals(match.visitingTeam) && dateTime.equals(match.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, visitingTeam, dateTime);
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", visitingTeam=" + visitingTeam +
                ", homeTeamScore=" + homeTeamScore +
                ", visitingTeamScore=" + visitingTeamScore +
                ", dateTime=" + dateTime +
                '}';
    }
}
