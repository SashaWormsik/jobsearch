package org.chervyakovsky.jobsearch.model.mapper.impl;

import org.chervyakovsky.jobsearch.DataProviderTest;
import org.chervyakovsky.jobsearch.model.mapper.MapperFromRequestToEntityTest;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CredentialMapperFromRequestToEntityTest extends MapperFromRequestToEntityTest {

    @BeforeClass
    @Override
    public void setMapper() {
        mapper = new CredentialMapperFromRequestToEntity();
    }

    @Override
    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testMap(RequestContent requestContent) {
        Assert.assertTrue(mapper.map(requestContent).getId() == 0);
    }
}