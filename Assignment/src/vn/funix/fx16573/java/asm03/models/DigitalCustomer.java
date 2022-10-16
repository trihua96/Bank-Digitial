package vn.funix.fx16573.java.asm03.models;

import vn.funix.fx16573.java.asm02.models.Account;
import vn.funix.fx16573.java.asm02.models.Customer;

public class DigitalCustomer extends Customer {

    // Hàm rút tiền: Truy xuất đối tượng cùng ID và rút tiền ID đó
    /**
     * @param accountNumber
     * @param amount
     *  Tham số rút tiền theo số tài khoản và một khoản tiền rút
     * */
    public void withdrawMoney(String accountNumber, double amount, String id) throws Exception {
        boolean checkAccount = false;
        for(Account account : super.getAccounts()) {
            if(account instanceof Withdraw  && account.getAccountNumber().equals(accountNumber)) {
               if(((Withdraw) account).withdraw(amount, id)) {
                   checkAccount = true;
               } else {
                   throw new Exception("---- Số tiền rút không hợp lệ! ----");
               }
            }
        }
        if(!checkAccount) throw new Exception("---- Số tài khoản không tồn tại! ----");
    }


    // Hàm hiển thị khách hàng và tài khoản
    @Override
    public void displayInformation() {
        // Hiển thị khách hàng
        /**
         * In thông tin khách hàng
         * Số CCCD      |  Tên khách hàng |    loại khách hàng |   Số dư
         * 096096000001 |  Funix          |    Normal/ Premium |   1,000,000 đ
         * */
        System.out.println(super.getCustomerId() + "  |\t\t\t" + super.getName() + " | " + super.isPremium() + " |\t" + Utils.formatBalance(super.getSumBalance()));

        // Hiển thị toàn bộ tài khoản của khách hàng
        for(int i = 0; i < super.getAccounts().size(); i++) {
            /**
             *  Số tài khoản | Loại tài khoản |      Số dư
             *  123321       | SAVINGS/ LOAN  |      50,000 đ
             * */
            System.out.println((i + 1) + "\t   " + super.getAccounts().get(i).toString());
        }

    }
}
