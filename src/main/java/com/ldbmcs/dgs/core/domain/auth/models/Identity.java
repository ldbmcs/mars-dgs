package com.ldbmcs.dgs.core.domain.auth.models;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ldbmcs.dgs.infra.mybatis.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("identity")
@EqualsAndHashCode(callSuper = true)
public class Identity extends BaseEntity {
    private String userId;
    private String credential;
    private String principal;

    public static Identity of(String userId, String principal, String credential) {
        Identity identity = new Identity();
        identity.setUserId(userId);
        identity.setCredential(credential);
        identity.setPrincipal(principal);
        return identity;
    }
}
