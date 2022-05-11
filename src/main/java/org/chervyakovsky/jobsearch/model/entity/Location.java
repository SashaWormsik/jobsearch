package org.chervyakovsky.jobsearch.model.entity;

public class Location extends AbstractEntity {

    private String country;
    private String city;

    public Location() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        Location location = (Location) o;
        if (this.country != null ? !this.country.equals(location.country) : location.country != null) {
            return false;
        }
        if (this.city != null ? !this.city.equals(location.city) : location.city != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.country != null ? this.country.hashCode() : 0);
        result = prime * result + (this.city != null ? this.city.hashCode() : 0);
        return result;
    }
}
