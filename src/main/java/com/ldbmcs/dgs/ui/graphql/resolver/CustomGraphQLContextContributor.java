package com.ldbmcs.dgs.ui.graphql.resolver;

import cn.hutool.core.util.StrUtil;
import com.ldbmcs.dgs.core.domain.auth.service.AuthTokenDomainService;
import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.infra.context.CurrentContext;
import com.netflix.graphql.dgs.context.GraphQLContextContributor;
import com.netflix.graphql.dgs.internal.DgsRequestData;
import graphql.GraphQLContext;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomGraphQLContextContributor implements GraphQLContextContributor {
    @Autowired
    private AuthTokenDomainService authTokenDomainService;

    private static final String CURRENT_USER = "CurrentUser";

    @Override
    public void contribute(@NotNull GraphQLContext.Builder builder, @Nullable Map<String, ?> extensions,
                           @Nullable DgsRequestData dgsRequestData) {
        if (dgsRequestData != null && dgsRequestData.getHeaders() != null) {
            String authorization = dgsRequestData.getHeaders().getFirst("Authorization");
            if (StrUtil.isBlank(authorization)) {
                return;
            }

            User currentUser = authTokenDomainService.parse(authorization);
            if (currentUser != null) {
                CurrentContext.setAuthToken(authorization);
                CurrentContext.setUser(currentUser);
                builder.put(CURRENT_USER, currentUser);
            }
        }
    }
}
