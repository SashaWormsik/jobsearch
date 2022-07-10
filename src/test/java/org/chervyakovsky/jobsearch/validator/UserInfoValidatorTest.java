package org.chervyakovsky.jobsearch.validator;

import org.chervyakovsky.jobsearch.DataProviderTest;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserInfoValidatorTest {

    private UserInfoValidator validator = UserInfoValidator.getInstance();

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidUserInfoData(RequestContent requestContent) {
        Assert.assertTrue(validator.isValidUserInfoData(requestContent));
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidatePasswordAndConfirmPassword(RequestContent requestContent) {
        Assert.assertTrue(validator.isValidatePasswordAndConfirmPassword(requestContent));
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidLoginUserData(RequestContent requestContent) {
        Assert.assertTrue(validator.isValidLoginUserData(requestContent));
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderTest.class)
    public void testIsValidRegistrationUserData(RequestContent requestContent) {
        Assert.assertTrue(validator.isValidRegistrationUserData(requestContent));
    }
}