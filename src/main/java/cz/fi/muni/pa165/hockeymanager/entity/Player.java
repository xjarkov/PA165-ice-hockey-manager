package cz.fi.muni.pa165.hockeymanager.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @ManyToOne
    @Getter
    @Setter
    private Team team;

    public Player() {}

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
