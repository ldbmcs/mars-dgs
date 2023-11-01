package com.ldbmcs.dgs.infra.mybatis.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class BaseEntity implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    protected String id;

    protected String createdBy;

    protected OffsetDateTime createdAt;

    @TableLogic
    protected OffsetDateTime deletedAt;
}
