package org.chervyakovsky.jobsearch.model.mapper;

import org.chervyakovsky.jobsearch.DataProviderTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class MapperFromRequestToEntityTest {

    protected MapperFromRequestToEntity mapper;

    @BeforeClass
    public abstract void setMapper();

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testMap(RequestContent requestContent) {
        Assert.assertTrue(mapper.map(requestContent).getId() != 0);
    }
}