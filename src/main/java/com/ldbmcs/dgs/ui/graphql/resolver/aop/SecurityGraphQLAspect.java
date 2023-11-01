package com.ldbmcs.dgs.ui.graphql.resolver.aop;

import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.core.shared.Errors;
import com.ldbmcs.dgs.infra.context.CurrentContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityGraphQLAspect {

    @Before("isSecuredResolverMethod()")
    public void doSecurityCheck() {
        User currentUser = CurrentContext.user();
        if (currentUser == null) {
            throw Errors.unauthorized();
        }
    }

    @Pointcut("target(com.ldbmcs.dgs.ui.graphql.resolver.SecuredResolver) && execution(public * *(..))")
    private void isSecuredResolverMethod() {
    }
}
