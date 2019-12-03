package com.cvte.customer_service.cuse.entity;

import java.util.Date;

public class CustomerServiceLog {
    private Long id;

    private String uid;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String questionUid;

    private String userUid;

    public CustomerServiceLog(String uid, String questionUid, String userUid) {
        this.uid = uid;
        this.questionUid = questionUid;
        this.userUid = userUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getQuestionUid() {
        return questionUid;
    }

    public void setQuestionUid(String questionUid) {
        this.questionUid = questionUid == null ? null : questionUid.trim();
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    @Override
    public String toString() {
        return "CustomerServiceLog{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", questionUid='" + questionUid + '\'' +
                ", userUid='" + userUid + '\'' +
                '}';
    }
}