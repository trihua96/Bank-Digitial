package vn.funix.fx16573.java.asm02.models;

import vn.funix.fx16573.java.asm03.models.Transaction;
import vn.funix.fx16573.java.asm03.models.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;

    private List<Transaction> transactionList = new ArrayList<Transaction>();

    public Account() {}

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Hàm lấy số tài khoản
    public String getAccountNumber() {
        return accountNumber;
    }

    // Hàm cập nhật số tài khoản
    public void setAccountNumber(String accountNumber) throws Exception {
        // Kiểm tra STK phải gồm 6 số
        boolean isAccountNumber = false;
        if (accountNumber.length() == 6) {
            // Kiểm tra STK có ký tự không
            for (int i = 0; i < accountNumber.length(); i++) {
                if ((accountNumber.charAt(i) >= '0') && (accountNumber.charAt(i) <= '9')) {
                    isAccountNumber = true;
                } else {
                    throw new Exception("STK phải là số! Xin nhập lại.");
                }
            }
        } else {
            throw new Exception("STK phải gồm 6 chữ số.");
        }

        if(isAccountNumber) {
            this.accountNumber = accountNumber;
        }
    }

    // Hàm lấy số dư
    public double getBalance() {
        return balance;
    }

    // Hàm cập nhật số dư
    public void setBalance(double balance) throws Exception{
        // Số dư nhập vào phải tối thiểu 50000
        if (balance >= 50_000) {
            this.balance = balance;
        } else {
            throw new Exception("Số dư phải 50.000 trở lên. Xin nhập lại!");
        }
    }

    // Hàm check loại tài khoản
    public boolean isPremium() {
        if(this.balance >= 10_000_000) {
            return true;
        }
        return false;
    }

    // Hàm Lấy phí
    public double getTransactionFee() {
        return 0.0;
    }

    // Hàm in lịch sử giao dịch
    public void viewTransactionHistory() {
        // Duyệt danh sách in thông tin giao dịch
        for(Transaction transaction : transactionList) {
            System.out.printf("%s  |%15s | %s | %s%n", transaction.getAccountNumber(), Utils.formatBalance(-transaction.getAmount()), transaction.getDate(), transaction.getId());
        }

    }

    // Hàm in tài khoản
    @Override
    public String toString() {
        DecimalFormat vn = Bank.formatCurrency();

        /**
         *  Số tài khoản |          Số dư
         *  123321       |          50,000 đ
         * */
        return accountNumber +" |\t\t\t\t\t\t" + vn.format(balance);
    }

    // Hàm thêm transaction
    public void addTransaction(boolean status, Transaction transaction) {
        // Cập nhật tình trạng giao dịch
        transaction.setStatus(status);

        // Thêm đối tượng transaction vào List
        getTransactionList().add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
