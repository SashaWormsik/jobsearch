package org.chervyakovsky.jobsearch.model.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Cloneable {

    private long id;

    public AbstractEntity() {

    }

    public AbstractEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity abstractEntity = (AbstractEntity) o;
        return id == abstractEntity.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
