package com.graduate.engine.model;

public class Guest {
    private Integer guestId;

    private String guestName;

    private String guestSex;

    private String guestEmail;

    private String guestPhone;

    private Integer lastLogin;

    private Integer expirePeriod;

    private Integer registerDate;

    private String memo;

    private Boolean stop;

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName == null ? null : guestName.trim();
    }

    public String getGuestSex() {
        return guestSex;
    }

    public void setGuestSex(String guestSex) {
        this.guestSex = guestSex == null ? null : guestSex.trim();
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail == null ? null : guestEmail.trim();
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone == null ? null : guestPhone.trim();
    }

    public Integer getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Integer lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getExpirePeriod() {
        return expirePeriod;
    }

    public void setExpirePeriod(Integer expirePeriod) {
        this.expirePeriod = expirePeriod;
    }

    public Integer getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Integer registerDate) {
        this.registerDate = registerDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}