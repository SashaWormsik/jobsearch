package org.chervyakovsky.jobsearch.model.entity;

import org.chervyakovsky.jobsearch.model.entity.status.EducationStatus;
import org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus;
import org.chervyakovsky.jobsearch.model.entity.status.WorkingStatus;

public class UserInfo extends AbstractEntity {

    private String login;
    private String email;
    private UserRoleStatus role;
    private Boolean userStatus = false; // FIXME
    private Long locationId;
    private String userName;
    private String userSurName;
    private WorkingStatus workingStatus;
    private EducationStatus education;
    private String profession;
    private String description;
    private String userToken;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRoleStatus getRole() {
        return role;
    }

    public void setRole(UserRoleStatus role) {
        this.role = role;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(WorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

    public EducationStatus getEducation() {
        return education;
    }

    public void setEducation(EducationStatus education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
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
        UserInfo userInfo = (UserInfo) o;

        if (this.locationId != null ? !this.locationId.equals(userInfo.locationId) : userInfo.locationId != null) {
            return false;
        }
        if (this.login != null ? !this.login.equals(userInfo.login) : userInfo.login != null) {
            return false;
        }
        if (this.email != null ? !this.email.equals(userInfo.email) : userInfo.email != null) {
            return false;
        }
        if (this.role != null ? !this.role.equals(userInfo.role) : userInfo.role != null) {
            return false;
        }
        if (this.userName != null ? !this.userName.equals(userInfo.userName) : userInfo.userName != null) {
            return false;
        }
        if (this.workingStatus != null ? !this.workingStatus.equals(userInfo.workingStatus) : userInfo.workingStatus != null) {
            return false;
        }
        if (this.education != null ? !this.education.equals(userInfo.education) : userInfo.education != null) {
            return false;
        }
        if (this.userSurName != null ? !this.userSurName.equals(userInfo.userSurName) : userInfo.userSurName != null) {
            return false;
        }
        if (this.profession != null ? !this.profession.equals(userInfo.profession) : userInfo.profession != null) {
            return false;
        }
        if (this.description != null ? !this.description.equals(userInfo.description) : userInfo.description != null) {
            return false;
        }
        if (this.userToken != null ? !this.userToken.equals(userInfo.userToken) : userInfo.userToken != null) {
            return false;
        }
        if (this.userStatus != null ? !this.userStatus.equals(userInfo.userStatus) : userInfo.userStatus != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.login != null ? this.login.hashCode() : 0);
        result = prime * result + (this.email != null ? this.email.hashCode() : 0);
        result = prime * result + (this.role != null ? this.role.hashCode() : 0);
        result = prime * result + (Long.hashCode(locationId));
        result = prime * result + (this.userName != null ? this.userName.hashCode() : 0);
        result = prime * result + (this.userSurName != null ? this.userSurName.hashCode() : 0);
        result = prime * result + (this.workingStatus != null ? this.workingStatus.hashCode() : 0);
        result = prime * result + (this.education != null ? this.education.hashCode() : 0);
        result = prime * result + (this.profession != null ? this.profession.hashCode() : 0);
        result = prime * result + (this.description != null ? this.description.hashCode() : 0);
        result = prime * result + (this.userToken != null ? this.userToken.hashCode() : 0);
        return result;
    }
}
