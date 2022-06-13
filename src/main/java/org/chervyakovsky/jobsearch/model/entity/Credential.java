package org.chervyakovsky.jobsearch.model.entity;

import java.util.Date;

public class Credential extends AbstractEntity {

    private String password;
    private Boolean active = true;
    private Date createDate = new Date();
    private Long userInfoId;

    public Credential() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Credential credential = (Credential) o;
        if (this.active != null ? !this.active.equals(credential.active) : credential.active != null) {
            return false;
        }
        if (this.userInfoId != null ? !this.userInfoId.equals(credential.userInfoId) : credential.userInfoId != null) {
            return false;
        }
        if (this.createDate != null ? !this.createDate.equals(credential.createDate) : credential.createDate != null) {
            return false;
        }
        if (this.password != null ? !this.password.equals(credential.password) : credential.password != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.active != null ? active.hashCode() : 0);
        result = prime * result + (this.userInfoId != 0 ? userInfoId.hashCode() : 0);
        result = prime * result + (this.password != null ? this.password.hashCode() : 0);
        result = prime * result + (this.createDate != null ? this.createDate.hashCode() : 0);
        return result;
    }
}
