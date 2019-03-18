package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestVo {
    private String loginName;

    private Long guestId;

    private String guestName;

    private String guestSex;

    private String guestEmail;

    private String guestPhone;

    private Long lastLogin;

    private Integer expirePeriod;

    private Long registerDate;

    private String memo;

    private Boolean stop;
}
