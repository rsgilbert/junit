package mocks;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAccountService {

    @Test
    public void testTransferOk() {
        MockAccountManager mockAccountManager = new MockAccountManager();
        Account senderAccount = new Account("10", 400);
        Account beneficiaryAccount = new Account("50", 80);
        mockAccountManager.addAccount("10", senderAccount);
        mockAccountManager.addAccount("50", beneficiaryAccount);
        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("10", "50", 30);

        assertEquals("Sender balance must be correct", 370, senderAccount.getBalance());
        assertEquals("Beneficiary balance must correct", 110, beneficiaryAccount.getBalance());

    }
}
