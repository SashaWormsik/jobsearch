package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.DataProviderTest;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VacancyValidatorTest {

    private VacancyValidator validator = VacancyValidator.getInstance();

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidVacancyData(RequestContent requestContent) {
        Assert.assertTrue(validator.isValidVacancyData(requestContent));
    }
}