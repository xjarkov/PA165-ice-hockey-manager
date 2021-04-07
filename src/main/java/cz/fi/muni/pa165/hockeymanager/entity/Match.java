package cz.fi.muni.pa165.hockeymanager.entity;

import java.sql.Time;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lukas Machalek (485196)
 */
@Entity
@Table(name = "IceHockeyMatch")
public class Match {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    private Team homeTeam;


    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    private Team visitingTeam;

    @NotNull
    @Getter
    @Setter
    private int homeTeamScore;

    @NotNull
    @Getter
    @Setter
    private int visitingTeamScore;

    @NotNull
    @Getter
    private Date dateTime;

    public Match(){}

    public Match(Team homeTeam, Team visitingTeam, Date dateTime) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.dateTime = dateTime;

        homeTeamScore = 0;
        visitingTeamScore = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return id == match.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)id;
        return result;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", visitingTeam=" + visitingTeam +
                ", homeTeamScore=" + homeTeamScore +
                ", visitingTeamScore=" + visitingTeamScore +
                ", date=" + dateTime +
                '}';
    }
}
