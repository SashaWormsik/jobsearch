package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.DataProviderTest;
import org.chervyakovsky.jobsearch.exception.ServiceException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InterviewValidatorTest {

    private InterviewValidator validator = InterviewValidator.getInstance();

   @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidInterviewData(RequestContent requestContent) throws ServiceException {
        Assert.assertTrue(validator.isValidInterviewData(requestContent));
    }
}