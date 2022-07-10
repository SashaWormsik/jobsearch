package org.chervyakovsky.jobsearch.model.service.impl;

import org.chervyakovsky.jobsearch.model.dao.CredentialDao;
import org.chervyakovsky.jobsearch.model.dao.impl.CredentialDaoImpl;
import org.chervyakovsky.jobsearch.model.service.CredentialService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import static org.mockito.BDDMockito.given;
import static org.testng.Assert.*;

public class CredentialServiceImplTest {
    @Mock
    private CredentialDao dao = CredentialDaoImpl.getInstance();
    private CredentialService service = CredentialServiceImpl.getInstance();

    public CredentialServiceImplTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdatePasswordByUserId() {

    }
}