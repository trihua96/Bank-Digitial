package vn.funix.fx16573.java.asm03.models;

import vn.funix.fx16573.java.asm02.models.Account;

public class LoansAccount extends Account implements Withdraw, ReportService{
    private final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private final double LOAN_ACCOUNT_MAX_BALANCE = 100_000_000;

    public LoansAccount() {}

    public LoansAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     *  Hàm rút tiền
     *  @Param amount để set lại giá trị số dư
     * */
    @Override
    public boolean withdraw(double amount, String id) throws Exception {
        // Khởi tại đối tượng transaction
        Transaction transaction = new Transaction(id, getAccountNumber(), amount, Utils.getDateTime(), false);

        // Lưu phí
        double storedFee = getTransactionFee();

        // Tính số dư với amount và phí
        double newBalance = getBalance() - (amount + amount * storedFee);
        if(isAccepted(amount)) {
            // cập nhật số dư
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
     * Hạn mức không được qúa giới hạn 100.000.000đ
     * Phí cho mỗi lần giao dịch là 0.05 trên số tiền giao dịch đối với tài khoản thường và 0.01 trên số tiền giao dịch đối với tài khoản Premium.
     * Hạn mức còn lại sau khi rút tiền không được nhỏ hơn 50.000đ
     */
    @Override
    public boolean isAccepted(double amount) {
        if( (amount >= 50_000) &&  (amount % 10_000 == 0)) {
            if(((getBalance() - amount) >= 50_000) && (amount <= 100_000_000)){
                return true;
            }
        }
        return false;
    }

    // Hàm lấy phí
    /**
     *  trả về phí theo loại tài khoản
     * */
    @Override
    public double getTransactionFee() {
        // Phí TK Premium
        if(isPremium()) return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        // Phí TK Normal
        return LOAN_ACCOUNT_WITHDRAW_FEE;
    }

    // Hàm in biên lai
    /**
     * @Param amount để in số tiền rút
     * @Param storedFee để tính toán phí và in ra
     * */
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
         *  123321       | Loan     |    50,000 đ
         * */
        return super.getAccountNumber() +" |\t\t  LOAN    |\t\t\t\t" + Utils.formatBalance(super.getBalance());
    }

    public String getTitle() {
        String title = "NGAN HANG SO\n\t\tBien Lai Giao Dich LOAN";

        return title;
    }
}
