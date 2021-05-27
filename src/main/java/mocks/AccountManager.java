package mocks;

/**
 * Interface for managing the lifecycle and persistence of Account objects
 */
public interface AccountManager {
    Account findAccountForUser(String userId);
    void updateAccount(Account account);
}
