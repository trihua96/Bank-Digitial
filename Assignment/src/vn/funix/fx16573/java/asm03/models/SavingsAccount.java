package vn.funix.fx16573.java.asm03.models;

import vn.funix.fx16573.java.asm02.models.Account;

public class SavingsAccount extends Account implements Withdraw, ReportService {
    private final int SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000;

    public SavingsAccount() {}

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     *  Hàm rút tiền
     *  @Param amount để set lại giá trị số dư
     * */
    @Override
    public boolean withdraw(double amount, String id) throws Exception {
        // Khởi tại đối tượng transaction
        Transaction transaction = new Transaction(id,getAccountNumber(), amount, Utils.getDateTime(), false);
        // Lưu phí
        double storedFee = getTransactionFee();

        // Tính số dư với amount và phí
        double newBalance = super.getBalance() - (amount + amount * storedFee);
        if(isAccepted(amount)) {
            // Cập nhật số dư
            super.setBalance(newBalance);

            // cập nhật vào lịch sử giao dịch
            addTransaction(true, transaction);

            // In biên lai
            log(amount, storedFee);
            return true;
        }
        return false;
    }
    /**
     *  Xử lý nghiệp vụ rút tiền
     *
     * Số tiền rút phải lớn hơn hoặc bằng 50.000đ
     * Số tiền 1 lần rút không được quá 5.000.000đ đối với tài khoản thường, và không giới hạn đối với tài khoản Premium.
     * Số dư còn lại sau khi rút phải lớn hơn hoặc bằng 50.000đ
     * Số tiền rút phải là bội số của 10.000đ
     */
    @Override
    public boolean isAccepted(double amount) {

        // Xử lý số tiền rút >= 50.000 và phải là bội số của 10.000
        if((amount >= 50_000) &&  (amount % 10_000 == 0)) {
            // Kiểm tra loại tài khoản và số dư phải >= 50.000
            if ((isPremium()) && ((super.getBalance() - amount) >= 50_000)) {
                // Loại TK premium
                return true;
            } else {
                // Loại TK normal
                /**
                 *     Số tiền rút phải từ 5,000,000 trở xuống
                 * */
                if ((amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Hàm Lấy phí
    @Override
    public double getTransactionFee() {
        return super.getTransactionFee();
    }

    // In biên lai rút tiền
    @Override
    public void log(double amount, double storedFee) {
        System.out.println(Utils.getDivider());
        System.out.println("\t\t\t" + getTitle());
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %31s%n", super.getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(super.getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(storedFee * amount));
        System.out.println(Utils.getDividerEnd());
    }


    // In danh sách tài khoản
    @Override
    public String toString() {
        /**
         *  Số tài khoản | Loại TK  |    Số dư
         *  123321       | Savings     |    50,000 đ
         * */
        return super.getAccountNumber() +" |\t\t  SAVINGS |\t\t\t\t " + Utils.formatBalance(super.getBalance());
    }

    public String getTitle() {
        String title = "NGAN HANG DIEN TU\n\t\tBien Lai Giao Dich SAVINGS";

        return title;
    }
}