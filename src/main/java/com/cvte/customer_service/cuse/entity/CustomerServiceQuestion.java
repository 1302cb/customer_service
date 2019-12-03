package com.cvte.customer_service.cuse.entity;

import java.util.Date;

public class CustomerServiceQuestion {
    private Long id;

    private String uid;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String userUid;

    private String questionContent;

    public CustomerServiceQuestion(String userUid, String questionContent) {
        this.userUid = userUid;
        this.questionContent = questionContent;
    }

    public CustomerServiceQuestion(String uid, String userUid, String questionContent) {
        this.uid = uid;
        this.userUid = userUid;
        this.questionContent = questionContent;
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

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    @Override
    public String toString() {
        return "CustomerServiceQuestion{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", userUid='" + userUid + '\'' +
                ", questionContent='" + questionContent + '\'' +
                '}';
    }
}