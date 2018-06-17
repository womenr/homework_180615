package wj.csv.pojo;

import java.math.BigDecimal;

public class Account {
    private String account;

    private Integer password;

    private BigDecimal balance;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

	@Override
	public String toString() {
		return "Account [account=" + account + ", password=" + password + ", balance=" + balance + "]";
	}
}