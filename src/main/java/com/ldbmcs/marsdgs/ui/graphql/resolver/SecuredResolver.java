package com.ldbmcs.marsdgs.ui.graphql.resolver;

import com.ldbmcs.marsdgs.core.domain.user.models.User;
import com.ldbmcs.marsdgs.infra.context.CurrentContext;

public interface SecuredResolver extends SystemGraphQLResolver {
    default User getCurrentUser() {
        return CurrentContext.user();
    }
}
