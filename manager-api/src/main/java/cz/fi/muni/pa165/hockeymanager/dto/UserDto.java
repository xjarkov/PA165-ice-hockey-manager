package cz.fi.muni.pa165.hockeymanager.dto;

import java.util.Objects;
import javax.management.relation.Role;

/**
 * @author Lukas Machalek (485196)
 */
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private TeamDto team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(role, userDto.role) && Objects.equals(team, userDto.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, role, team);
    }
}
