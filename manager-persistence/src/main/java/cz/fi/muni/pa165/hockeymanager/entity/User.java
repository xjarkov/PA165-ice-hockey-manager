package cz.fi.muni.pa165.hockeymanager.entity;

import cz.fi.muni.pa165.hockeymanager.enums.Role;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Petr Šopf (506511)
 */
@Entity
@Table(name = "UserTable")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Role role = Role.PLAYER;

    @OneToOne(fetch= FetchType.LAZY)
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

    public User(String name, String email, String password, Role role) {
        this(name, email, password, role, null);
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
