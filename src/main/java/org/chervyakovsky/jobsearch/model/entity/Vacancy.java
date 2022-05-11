package org.chervyakovsky.jobsearch.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Vacancy extends AbstractEntity {

    Date createDate;
    String jobTitle;
    long companyId;
    long locationId;
    BigDecimal salary;
    long currencyId;
    String responsibilities;
    String requirement;
    String workingConditions;
    boolean vacancyStatus;

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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
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

    public boolean isVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(boolean vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
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
        if (this.companyId != vacancy.companyId || this.locationId != vacancy.locationId
                || this.currencyId != vacancy.currencyId || this.vacancyStatus != vacancy.vacancyStatus) {
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
        result = prime * result + (Long.hashCode(this.companyId));
        result = prime * result + (Long.hashCode(this.locationId));
        result = prime * result + (this.salary != null ? this.salary.hashCode() : 0);
        result = prime * result + (Long.hashCode(this.currencyId));
        result = prime * result + (this.responsibilities != null ? this.responsibilities.hashCode() : 0);
        result = prime * result + (this.requirement != null ? this.requirement.hashCode() : 0);
        result = prime * result + (this.workingConditions != null ? this.workingConditions.hashCode() : 0);
        result = prime * result + (Boolean.hashCode(this.vacancyStatus));
        return result;

    }
}
