package cz.fi.muni.pa165.hockeymanager.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"password"})
public class UserDtoMixin {
}
