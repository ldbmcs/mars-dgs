package com.ldbmcs.dgs.ui.graphql.resolver.dataloader;

import com.ldbmcs.dgs.core.domain.user.models.User;
import com.ldbmcs.dgs.core.domain.user.service.UserDomainService;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = DataLoaderIdentity.USER)
public class UserBatchLoader implements MappedBatchLoader<String, User> {

    @Autowired
    private UserDomainService userDomainService;

    @Override
    public CompletionStage<Map<String, User>> load(Set<String> ids) {
        return CompletableFuture.completedFuture(userDomainService.findAllById(ids));
    }
}
