package mocks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class TestAccountServiceWithEasyMock {
    private AccountManager mockAccountManager;

    @Before
    public void setup(){
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);

    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("A1", 100);
        Account beneficiaryAccount =  new Account("A2", 50);


        // Start defining expectations
        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        expect(mockAccountManager.findAccountForUser("A1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("A2")).andReturn(beneficiaryAccount);
        // We're done defining expectations
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("A1", "A2", 70);

        assertEquals("Sender account should have correct balance",
                30, senderAccount.getBalance());

        assertEquals("Beneficiary account should have correct balance",
                120, beneficiaryAccount.getBalance());

    }

    @After
    public void tearDown() {
        verify(mockAccountManager);
    }

}

