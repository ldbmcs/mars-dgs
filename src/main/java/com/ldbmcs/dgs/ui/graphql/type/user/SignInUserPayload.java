package com.ldbmcs.dgs.ui.graphql.type.user;

import com.ldbmcs.dgs.core.domain.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInUserPayload {
    private User user;
    private String token;
}
