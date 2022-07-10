package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntityTest;
import org.testng.annotations.BeforeClass;

public class InterviewMapperFromRequestToEntityTest extends MapperFromRequestToEntityTest {

    @BeforeClass
    @Override
    public void setMapper() {
        mapper = new InterviewMapperFromRequestToEntity();
    }
}