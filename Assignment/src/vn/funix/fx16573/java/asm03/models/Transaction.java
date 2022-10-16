package vn.funix.fx16573.java.asm03.models;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String date;

    private boolean status;

    public Transaction() {}

    // Khởi tạo transaction
    public Transaction(String id, String accountNumber, double amount, String date, boolean status) {
        this.id = id;
        this.date = date;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status = status;
    }

    // Lấy số tài khoản
    public String getAccountNumber() {
        return accountNumber;
    }

    // Lấy số tiền rút
    public double getAmount() {
        return amount;
    }

    // Lấy ngày tháng đã rút
    public String getDate() {
        return date;
    }


    // Lấy id ngẫu nhiên
    public String getId() {
        return id;
    }

    // Cập nhật tình trạng giao dịch
    public void setStatus(boolean status) {
        this.status = status;
    }
}
