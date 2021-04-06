package cz.fi.muni.pa165.hockeymanager.entity;

import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToOne
    @Getter
    @Setter
    private HumanPlayer manager;

    @OneToMany
    @Getter
    private List<Player> players = new ArrayList<>();

    @OneToMany
    @Getter
    private Queue<Match> matches = new Queue<>();

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
