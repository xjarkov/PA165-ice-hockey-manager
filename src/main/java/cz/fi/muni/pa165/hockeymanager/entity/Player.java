package cz.fi.muni.pa165.hockeymanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @Column(nullable = false)
    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private Integer offensiveStrength;

    @Getter
    @Setter
    private Integer deffensiveStrength;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Team team;

    public Player() {}

    public Player(String firstName, String lastName, Integer offStrength, Integer deffStrength, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.offensiveStrength = offStrength;
        this.deffensiveStrength = deffStrength;
        this.team = team;
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Player))
            return false;
        Player other = (Player) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;

    }
}
