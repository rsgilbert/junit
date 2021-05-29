package mocks;


import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.*;


/**
 * Test transfer method on AccountService using JMock
 */
public class TestAccountServiceWithJmock {
    private Mockery context = new JUnit4Mockery();
    private AccountManager mockAccountManager;

    @Before
    public void setup() {
        mockAccountManager = context.mock(AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        final Account senderAccount = new Account("1", 500);
        final Account beneficiaryAccount = new Account("2", 600);

        context.checking(new Expectations() {
            {
                oneOf(mockAccountManager).findAccountForUser("1");
                will(returnValue(senderAccount));

                oneOf(mockAccountManager).findAccountForUser("2");
                will(returnValue(beneficiaryAccount));

                oneOf(mockAccountManager).updateAccount(senderAccount);
                oneOf(mockAccountManager).updateAccount(beneficiaryAccount);
            }
        });

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 400);

        assertEquals("Sender must have correct balance", 100, senderAccount.getBalance());
        assertEquals("Beneficiary must have correct balance", 1000, beneficiaryAccount.getBalance());
    }
}
