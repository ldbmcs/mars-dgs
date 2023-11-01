package com.ldbmcs.dgs.ui.graphql.type.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpUserInput {
    @NotBlank
    private String name;
    @NotBlank
    private String mobile;
    @NotBlank
    private String password;
}
