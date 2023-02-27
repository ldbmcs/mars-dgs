package com.ldbmcs.marsdgs.ui.graphql.resolver;

import cn.hutool.core.util.StrUtil;
import com.ldbmcs.marsdgs.core.domain.auth.service.AuthTokenDomainService;
import com.ldbmcs.marsdgs.core.domain.user.models.User;
import com.ldbmcs.marsdgs.infra.context.CurrentContext;
import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomGraphQLContextBuilder implements DgsCustomContextBuilderWithRequest<CustomGraphQLContext> {
    @Autowired
    private AuthTokenDomainService authTokenDomainService;

    private static final String BEARER = "Bearer ";

    @Override
    public CustomGraphQLContext build(Map<String, ?> extensions, HttpHeaders headers, WebRequest webRequest) {
        CustomGraphQLContext customGraphQLContext = new CustomGraphQLContext();
        assert headers != null;
        String authorization = headers.getFirst("Authorization");
        if (StrUtil.isBlank(authorization)) {
            return customGraphQLContext;
        }

        String token = authorization.replace(BEARER, "");
        User currentUser = authTokenDomainService.parse(token);
        if (currentUser != null) {
            CurrentContext.setAuthToken(token);
            CurrentContext.setUser(currentUser);
            customGraphQLContext.addCurrentUser(currentUser);
        }
        return customGraphQLContext;
    }
}
