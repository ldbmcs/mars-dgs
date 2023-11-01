package com.ldbmcs.dgs.ui.graphql.resolver;

import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.infra.context.CurrentContext;

public interface SecuredResolver extends SystemGraphQLResolver {
    default User getCurrentUser() {
        return CurrentContext.user();
    }
}
