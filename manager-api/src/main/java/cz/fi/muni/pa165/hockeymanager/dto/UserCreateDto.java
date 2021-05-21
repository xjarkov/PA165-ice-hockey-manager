package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kristian Kosorin (456620)
 */
@Data
public class UserCreateDto {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
