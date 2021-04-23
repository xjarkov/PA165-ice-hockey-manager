package cz.fi.muni.pa165.hockeymanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
* @author Kristian Kosorin (456620)
*/
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @Getter
    @Setter
    private String lastName;

    @NotNull
    @Getter
    @Setter
    private Integer offensiveStrength;

    @NotNull
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

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (!(obj instanceof Player))
//            return false;
//        Player other = (Player) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.getId()))
//            return false;
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//        result = prime * result + ((offensiveStrength == null) ? 0 : offensiveStrength.hashCode());
//        result = prime * result + ((deffensiveStrength == null) ? 0 : deffensiveStrength.hashCode());
//        result = prime * result + ((team == null) ? 0 : team.hashCode());
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return firstName.equals(player.firstName) && lastName.equals(player.lastName) && offensiveStrength.equals(player.offensiveStrength) && deffensiveStrength.equals(player.deffensiveStrength) && Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, offensiveStrength, deffensiveStrength, team);
    }
}
