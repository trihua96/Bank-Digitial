package vn.funix.fx16573.java.asm02.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> accounts;

    public Customer(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Customer(String name, String customerId, List<Account> accounts) {
        super(name, customerId);
        this.accounts = accounts;
    }

    public Customer() {
        this.accounts = new ArrayList<Account>();
   }

    public List<Account> getAccounts() {
        return accounts;
    }


   // Hàm kiểm tra loại tài khoản
    public String isPremium() {
        for(Account account : getAccounts()) {
            if(account.isPremium() || (getSumBalance() >= 10_000_000)) {
                return "Premium";
            }
        }
        return "Normal";
    }

    // Hàm kiểm tra tài khoản đã tồn tại
    public boolean isAccountExisted(String accountNumber) {
        for(Account account : accounts) {
            if(account.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    // Hàm thêm tài khoản
    public void addAccount(Account newAccount) throws Exception {
/*        if(!isAccountExisted(newAccount.getAccountNumber())) {
            accounts.add(newAccount);
        } else {
            throw new Exception("STK đã tồn tại");
        }*/
        accounts.add(newAccount);
    }


    @Override
    public String toString() {
        // Chuyển đổi đơn vị tiền tệ sang VN
        DecimalFormat vn = Bank.formatCurrency();

        /**
         * In thông tin khách hàng
         * Số CCCD |    Tên khách hàng |    loại khách hàng |   Số dư
         * */
        return super.getCustomerId() + "  |\t\t\t" + super.getName() + " | " + isPremium() + " |\t" + vn.format(getSumBalance());
    }

    // Hiển thị thông tin khách hàng cùng với tài khoản
    public void displayInformation() {
        // Hiển thị khách hàng
        System.out.println(toString());
        // Hiển thị toàn bộ tài khoản của khách hàng
        for(int i = 0; i < accounts.size(); i++) {
            /**
             *      Hiển thị tài khoản
             *      STT: 1    accountNumber: 123456   |   Balance: 50,0000 đ
             * */
            System.out.println((i + 1)+ "\t\t" + accounts.get(i).toString());
        }

    }

    public double getSumBalance() {
        double sum = 0;
        for(Account account : accounts) {
            sum+= account.getBalance();
        }
        return sum;
    }
}
