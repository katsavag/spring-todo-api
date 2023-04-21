package com.katsadouros.springtodoapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserCreateDTO {

    @Pattern(regexp = "^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    private String username;

    @Email
    private String email;
}
