package mocks;

/**
 * Offers services related to Accounts and uses the AccountManager
 * to persist data to the database
 */
public class AccountService {
    private AccountManager accountManager;

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Method for transferring money between two accounts
     */
    public void transfer(String senderId, String beneficiaryId, long amount) {
        Account sender = this.accountManager.findAccountForUser(senderId);
        Account beneficiary = this.accountManager.findAccountForUser(beneficiaryId);
        sender.debit(amount);
        beneficiary.credit(amount);
        this.accountManager.updateAccount(sender);
        this.accountManager.updateAccount(beneficiary);
    }
}
