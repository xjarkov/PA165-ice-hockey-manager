package cz.fi.muni.pa165.hockeymanager.entity;

import cz.fi.muni.pa165.hockeymanager.enums.Championship;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Matus Jarkovic (456441)
 */
@Data
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Enumerated
    private Championship championship;

    @NotNull
    private Long points = 0L;

    @OneToOne
    private User manager = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    @Setter(AccessLevel.NONE)
    private final Set<HockeyPlayer> hockeyPlayers = new HashSet<>();

    @OneToMany
    @Setter(AccessLevel.NONE)
    private final Set<Match> matches = new HashSet<>();

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
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
