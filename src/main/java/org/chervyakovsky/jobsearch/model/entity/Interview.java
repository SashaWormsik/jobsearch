package org.chervyakovsky.jobsearch.model.entity;

import org.chervyakovsky.jobsearch.model.entity.status.EnumInterviewStatus;

import java.util.Date;

public class Interview extends AbstractEntity {

    private Date appointedDateTime;
    private EnumInterviewStatus interviewStatus;
    private long vacancyId;
    private long userInfoId;

    public Interview() {
    }

    public Date getAppointedDateTime() {
        return appointedDateTime;
    }

    public void setAppointedDateTime(Date appointedDateTime) {
        this.appointedDateTime = appointedDateTime;
    }

    public EnumInterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(EnumInterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.appointedDateTime != null ? this.appointedDateTime.hashCode() : 0);
        result = prime * result + (this.interviewStatus != null ? this.interviewStatus.hashCode() : 0);
        result = prime * result + (Long.hashCode(this.vacancyId));
        result = prime * result + (Long.hashCode(this.userInfoId));
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
        if (this.interviewStatus != null ? !this.interviewStatus.equals(interview.interviewStatus) : interview.interviewStatus != null) {
            return false;
        }
        if (this.userInfoId != interview.userInfoId || this.vacancyId != interview.vacancyId) {
            return false;
        }
        return true;
    }

}
