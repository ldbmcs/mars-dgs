package com.ldbmcs.dgs.core.domain.user.models;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ldbmcs.dgs.infra.mybatis.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("users")
public class User extends BaseEntity {
    private String name;
    private String mobile;
    private String email;

    public static User of(String mobile, String name) {
        User user = new User();
        user.setMobile(mobile);
        user.setName(name);
        return user;
    }
}
