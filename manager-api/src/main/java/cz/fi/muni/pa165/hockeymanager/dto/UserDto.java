package cz.fi.muni.pa165.hockeymanager.dto;

import cz.fi.muni.pa165.hockeymanager.enums.Role;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Lukas Machalek (485196)
 */
public class UserDto {

    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Role role;

    @Getter
    @Setter
    private TeamDto team;

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
