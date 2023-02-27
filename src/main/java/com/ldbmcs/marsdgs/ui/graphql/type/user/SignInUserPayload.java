package com.ldbmcs.marsdgs.ui.graphql.type.user;

import com.ldbmcs.marsdgs.core.domain.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInUserPayload {
    private User user;
    private String token;
}
