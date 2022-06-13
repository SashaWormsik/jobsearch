package org.chervyakovsky.jobsearch.model.entity;

import org.chervyakovsky.jobsearch.model.entity.status.WorkExperienceStatus;

import java.math.BigDecimal;
import java.util.Date;

public class Vacancy extends AbstractEntity {

    private Date createDate = new Date();
    private String jobTitle;
    private Long companyId;
    private Long locationId;
    private BigDecimal salary;
    private String currency;
    private WorkExperienceStatus workExperienceStatus;
    private String responsibilities;
    private String requirement;
    private String workingConditions;
    private Boolean vacancyStatus = true;

    public Vacancy() {
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currencyId) {
        this.currency = currencyId;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getWorkingConditions() {
        return workingConditions;
    }

    public void setWorkingConditions(String workingConditions) {
        this.workingConditions = workingConditions;
    }

    public Boolean getVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(Boolean vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
    }

    public WorkExperienceStatus getWorkExperienceStatus() {
        return workExperienceStatus;
    }

    public void setWorkExperienceStatus(WorkExperienceStatus workExperienceStatus) {
        this.workExperienceStatus = workExperienceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        if (this.workExperienceStatus != null ? !this.workExperienceStatus.equals(vacancy.workExperienceStatus) : vacancy.workExperienceStatus != null) {
            return false;
        }
        if (this.companyId != null ? !this.companyId.equals(vacancy.companyId) : vacancy.companyId != null) {
            return false;
        }
        if (this.locationId != null ? !this.locationId.equals(vacancy.locationId) : vacancy.locationId != null) {
            return false;
        }
        if (this.currency != null ? !this.currency.equals(vacancy.currency) : vacancy.currency != null) {
            return false;
        }
        if (this.vacancyStatus != null ? !this.vacancyStatus.equals(vacancy.vacancyStatus) : vacancy.vacancyStatus != null) {
            return false;
        }
        if (this.createDate != null ? !this.createDate.equals(vacancy.createDate) : vacancy.createDate != null) {
            return false;
        }
        if (this.jobTitle != null ? !this.jobTitle.equals(vacancy.jobTitle) : vacancy.jobTitle != null) {
            return false;
        }
        if (this.salary != null ? !this.salary.equals(vacancy.salary) : vacancy.salary != null) {
            return false;
        }
        if (this.responsibilities != null ? !this.responsibilities.equals(vacancy.responsibilities) : vacancy.responsibilities != null) {
            return false;
        }
        if (this.requirement != null ? !this.requirement.equals(vacancy.requirement) : vacancy.requirement != null) {
            return false;
        }
        if (this.workingConditions != null ? !this.workingConditions.equals(vacancy.workingConditions) : vacancy.workingConditions != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.createDate != null ? this.createDate.hashCode() : 0);
        result = prime * result + (this.jobTitle != null ? this.jobTitle.hashCode() : 0);
        result = prime * result + (this.companyId != null ? this.companyId.hashCode() : 0);
        result = prime * result + (this.locationId != null ? this.locationId.hashCode() : 0);
        result = prime * result + (this.salary != null ? this.salary.hashCode() : 0);
        result = prime * result + (this.currency != null ? this.currency.hashCode() : 0);
        result = prime * result + (this.responsibilities != null ? this.responsibilities.hashCode() : 0);
        result = prime * result + (this.requirement != null ? this.requirement.hashCode() : 0);
        result = prime * result + (this.workingConditions != null ? this.workingConditions.hashCode() : 0);
        result = prime * result + (this.vacancyStatus != null ? this.vacancyStatus.hashCode() : 0);
        result = prime * result + (this.workExperienceStatus != null ? this.workExperienceStatus.hashCode() : 0);
        return result;

    }
}
