package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jimmy
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "login_id", type = IdType.AUTO)
    private Long loginId;
    private String loginName;
    private String loginPassword;
    private Long personId;
    private Long guestId;
    private Boolean stop;
}
