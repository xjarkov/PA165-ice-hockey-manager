package cz.fi.muni.pa165.hockeymanager.entity;

import cz.fi.muni.pa165.hockeymanager.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Petr Šopf (506511)
 */
@Entity
public class User {

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

    @OneToOne
    @Getter
    @Setter
    private Team team;

    public User() {
    }

    public User(String name, String email, String password, Role role, Team team) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return name.equals(that.name) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
