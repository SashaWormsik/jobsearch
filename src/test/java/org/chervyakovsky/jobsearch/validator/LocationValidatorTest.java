package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.DataProviderTest;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocationValidatorTest {

    private LocationValidator validator = LocationValidator.getInstance();

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidLocationData(RequestContent requestContent) {
        Assert.assertTrue(validator.isValidLocationData(requestContent));
    }
}