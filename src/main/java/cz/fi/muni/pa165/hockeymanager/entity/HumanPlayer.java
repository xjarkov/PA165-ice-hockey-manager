package cz.fi.muni.pa165.hockeymanager.entity;

import cz.fi.muni.pa165.hockeymanager.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Petr Šopf (506511)
 */
@Entity
public class HumanPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true)
    @NotNull
    @Getter
    @Setter
    private String name;

    @Column(unique = true)
    @NotNull
    @Getter
    @Setter
    private String email;

    @NotNull
    @Getter
    @Setter
    private String password;

    @NotNull
    @Getter
    @Setter
    private Role role = Role.PLAYER;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Team team;

    public HumanPlayer() {
    }

    public HumanPlayer(String name, String email, String password, Role role, Team team) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Human player {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", role=" + role +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof HumanPlayer))
            return false;

        HumanPlayer other = (HumanPlayer) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;

        if (email == null) {
            if (other.getEmail() != null)
                return false;
        } else if (!email.equals(other.getEmail()))
            return false;

        return true;
    }
}
