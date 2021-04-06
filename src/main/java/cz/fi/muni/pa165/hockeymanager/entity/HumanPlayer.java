package cz.fi.muni.pa165.hockeymanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Petr Šopf (506511)
 */
@Entity
public class HumanPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter

    private String name;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean isAdmin = false;

    @Column
    @OneToOne
    @Getter
    @Setter
    private Team team;

    public HumanPlayer() {
    }

    public HumanPlayer(String name, String email, String password, boolean isAdmin, Team team) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Human player {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", isAdmin=" + isAdmin +
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
