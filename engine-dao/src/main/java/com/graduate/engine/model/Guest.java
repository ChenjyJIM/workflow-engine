package com.graduate.engine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guest {
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
