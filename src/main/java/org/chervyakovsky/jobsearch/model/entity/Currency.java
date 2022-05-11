package org.chervyakovsky.jobsearch.model.entity;

public class Currency extends AbstractEntity {

    String isoName;

    public Currency() {
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.isoName != null ? this.isoName.hashCode() : 0);
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
        Currency currency = (Currency) o;
        if (this.isoName != null ? !this.isoName.equals(currency.isoName) : currency.isoName != null) {
            return false;
        }
        return true;
    }
}
