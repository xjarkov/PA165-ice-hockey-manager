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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
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

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private HumanPlayer manager = null;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private Set<Player> players = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private Set<Match> matches = new HashSet<>();

    public Team() {
    }

    public Team(String name, Championship championship) {
        this.name = name;
        this.championship = championship;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", championship=" + championship +
                ", points=" + points +
                ", manager=" + manager +
                ", players=" + players +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Team))
            return false;
        Team other = (Team) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
