package wj.csv.pojo;

public class Account {
    private String account;

    private Integer aPassword;

    private Integer balance;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getaPassword() {
        return aPassword;
    }

    public void setaPassword(Integer aPassword) {
        this.aPassword = aPassword;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}