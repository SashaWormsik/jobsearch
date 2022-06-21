package org.chervyakovsky.jobsearch.model.entity;

import org.chervyakovsky.jobsearch.model.entity.status.InterviewStatus;

import java.util.Date;

public class Interview extends AbstractEntity {

    private Date appointedDateTime;
    private InterviewStatus interviewStatus;
    private Long vacancyId;
    private Long userInfoId;
    private String communicationMethod;

    public Interview() {
    }

    public String getCommunicationMethod() {
        return communicationMethod;
    }

    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

    public Date getAppointedDateTime() {
        return appointedDateTime;
    }

    public void setAppointedDateTime(Date appointedDateTime) {
        this.appointedDateTime = appointedDateTime;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.appointedDateTime != null ? this.appointedDateTime.hashCode() : 0);
        result = prime * result + (this.communicationMethod != null ? this.communicationMethod.hashCode() : 0);
        result = prime * result + (this.interviewStatus != null ? this.interviewStatus.hashCode() : 0);
        result = prime * result + (this.vacancyId != null ? this.vacancyId.hashCode() : 0);
        result = prime * result + (this.userInfoId != null ? this.userInfoId.hashCode() : 0);
        return result;
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
        Interview interview = (Interview) o;
        if (this.appointedDateTime != null ? !this.appointedDateTime.equals(interview.appointedDateTime) : interview.appointedDateTime != null) {
            return false;
        }
        if (this.communicationMethod != null ? !this.communicationMethod.equals(interview.communicationMethod) : interview.communicationMethod != null) {
            return false;
        }
        if (this.interviewStatus != null ? !this.interviewStatus.equals(interview.interviewStatus) : interview.interviewStatus != null) {
            return false;
        }
        if (this.vacancyId != null ? !this.vacancyId.equals(interview.vacancyId) : interview.vacancyId != null) {
            return false;
        }
        if (this.userInfoId != null ? !this.userInfoId.equals(interview.userInfoId) : interview.userInfoId != null) {
            return false;
        }
        return true;
    }

}
