package cz.fi.muni.pa165.hockeymanager.entity;

import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Matus Jarkovic (456441)
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Column(unique = true)
    @Getter
    @Setter
    private String name;

    @NotNull
    @Enumerated
    @Getter
    @Setter
    private Championship championship;

    @NotNull
    @Getter
    @Setter
    private Long points = 0L;

    @OneToOne
    @Getter
    @Setter
    private User manager = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    @Getter
    private Set<HockeyPlayer> hockeyPlayers = new HashSet<>();

    @OneToMany
    @Getter
    private Set<Match> matches = new HashSet<>();

    public Team() {
    }

    public Team(String name, Championship championship) {
        this.name = name;
        this.championship = championship;
    }

    public void addPlayer(HockeyPlayer hockeyPlayer) {
        hockeyPlayers.add(hockeyPlayer);
    }

    public void removePlayer(HockeyPlayer hockeyPlayer) {
        hockeyPlayers.remove(hockeyPlayer);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void removeMatch(Match match) {
        matches.remove(match);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", championship=" + championship +
                ", points=" + points +
                ", manager=" + manager +
                ", players=" + hockeyPlayers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
