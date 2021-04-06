package cz.fi.muni.pa165.hockeymanager.entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Match {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @ManyToOne
    @Getter
    private Team homeTeam;

    @ManyToOne
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
    private Date date;

    @NotNull
    @Getter
    private Time time;

    public Match(){}

    public Match(Team homeTeam, Team visitingTeam, Date date, Time time) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.time = time;

        homeTeamScore = 0;
        visitingTeamScore = 0;
    }

    public void simulateMatch(){
        int max = 5;
        int min = 0;
        visitingTeamScore = (int) (Math.random() * (max - min)) + min;
        homeTeamScore = (int) (Math.random() * (max - min)) + min;

    }

    public void addHomeScore(){
        homeTeamScore++;
    }

    public void addVisitingScore(){
        visitingTeamScore++;
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

}
