package com.ldbmcs.dgs.ui.graphql.type.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignInUserInput {
    @NotBlank
    private String principal;
    @NotNull
    private String credential;
}
