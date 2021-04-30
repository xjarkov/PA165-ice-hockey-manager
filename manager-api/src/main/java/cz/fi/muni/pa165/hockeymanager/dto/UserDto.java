package cz.fi.muni.pa165.hockeymanager.dto;

import cz.fi.muni.pa165.hockeymanager.enums.Role;
import java.util.Objects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Lukas Machalek (485196)
 */
@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private TeamDto team;
}
