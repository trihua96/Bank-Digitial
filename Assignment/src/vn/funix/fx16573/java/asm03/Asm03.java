package vn.funix.fx16573.java.asm03;

import vn.funix.fx16573.java.asm02.Asm02;
import vn.funix.fx16573.java.asm02.models.Account;
import vn.funix.fx16573.java.asm02.models.Customer;
import vn.funix.fx16573.java.asm03.models.*;

import java.util.Scanner;

public class Asm03 {

    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "096096000001";
    private static final String CUSTOMER_NAME = "FUNIX";
    private static final String AUTHOR = "FX16573";
    private static final String VERSION = "@V3.0.0";

    public static void main(String[] args) {

        // Tạo sẵn Customer
        try {
            // Cập nhật customer
            DigitalCustomer newDigitalCustomer = new DigitalCustomer();
            newDigitalCustomer.setCustomerId(CUSTOMER_ID);
            newDigitalCustomer.setName(CUSTOMER_NAME);
            // Thêm mới customer
            activeBank.addCustomer(newDigitalCustomer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean inputAgain = false; // Biến chính để xử lý Tiếp tục(Thoát) chương trình
        while(!inputAgain) {
            // Vòng lặp true cho người dùng quay lại chọn chức năng
            while (true) {
                try {
                    // Hiển thị menu
                    displayMenu();

                    // Lựa chọn chức năng
                    System.out.println("Xin chọn chức năng: ");
                    int numberInput = Integer.parseInt(scanner.nextLine());

                    selectOptions(numberInput);

                    // Thoát chương trình
                    if (numberInput == 0) {
                        inputAgain = true; // Gán biến vòng lăp chính của chương trình
                        break;
                    }

                    if (numberInput >= 1 && numberInput <= 5) {
                        // Thoát vòng lặp chọn chức năng để xử lý cho chọn Tiếp tục(Thoát) chương trình
                        break;
                    }

                } catch (Exception e) {
                    if(e.getMessage() == null) {
                        System.out.println("\t\t--- Mời bạn chọn chức năng ---");
                    } else {
                        System.out.println("Xin nhập số để chọn chức năng!");
                    }
                }
            }

            // Xử lý tiếp tục(Thoát) chương trình
            if(!inputAgain) inputAgain = Asm02.handleOptionsAgain(1);

            // Tạm biệt và thoát chương trình
            if(inputAgain) {
                System.out.println("\t\t------- See you soon --------");
            }
        }
    }

    // Hàm xử lý các chức năng
    public static void selectOptions(int numberInput) throws Exception {

        // Cần vòng lặp cho xử lý nhập lại trong các hàm chức năng
        // Tạo biến xử lý kết thúc vòng lặp khi xong 1 hàm chức năng
        boolean checkFinish = false;
        while (true) {
            try {
                switch (numberInput) {
                    case 1:
                        // Hiển thị thông tin khách hàng
                        showCustomer();
                        checkFinish = true;
                        break;
                    case 2:
                        // Thêm tài khoản ATM
                        Account newSavingsAccount = savingsAccount();
                        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount(newSavingsAccount.getAccountNumber(), newSavingsAccount.getBalance()));
                        checkFinish = true;
                        break;
                    case 3:
                        // Thêm TK tín dụng
                        Account newLoanAccount = LoanAccount();
                        activeBank.addAccount(CUSTOMER_ID, new LoansAccount(newLoanAccount.getAccountNumber(), 10_000_000));
                        checkFinish = true;
                        break;
                    case 4:
                        // Rút tiền
                        withdraw();
                        checkFinish = true;
                        break;
                    case 5:
                        // Xem lịch sử giao dịch
                        activeBank.viewTransactionHistory(CUSTOMER_ID);
                        checkFinish = true;
                        break;
                    case 0:
                        // Thoát
                        checkFinish = true;
                        break;
                    default:
                        System.out.println("Hãy chọn đúng số!");
                        checkFinish = true;
                        break;
                }
            } catch (Exception e) {
                if(e.getMessage() == null) {
                    throw new Exception();
                } else {
                    // Xử lý lỗi từ các hàm chức năng trên
                    System.out.println(e.getMessage());
                }
            }

            // Thoát sau khi chức năng hoàn thành
            if(checkFinish) {
                break;
            }
        }
    }

    /**
     *  Hàm hiển thị Menu
     */
    public static void displayMenu() {
        System.out.println(Utils.getDivider());
        System.out.printf("| NGAN HANG DIEN TU | " + AUTHOR + VERSION + "%12s%n", "|");
        System.out.println(Utils.getDivider());
        System.out.println("1. Thông tin khách hàng");
        System.out.println("2. Thêm tài khoản ATM");
        System.out.println("3. Thêm tài khoản tín dụng");
        System.out.println("4. Rút tiền");
        System.out.println("5. Lịch sử giao dịch");
        System.out.println("0. Thoát");
        System.out.println(Utils.getDivider());
    }

    /**
     *  Hiển thị thông tin khách hàng
     */
    private static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if(customer != null) {
            customer.displayInformation();
        } else {
            System.out.println("Không có khách hàng nào hết....");
        }
    }

    /**
     *  Xử lý nghiệp vụ tài khoản Savings
     *
     * @Param Account
     */
    public static Account savingsAccount() throws Exception {
        Account savingsAccount = new SavingsAccount();

        // Cài đặt giá trị số tài khoản
        while(true) {
            try {
                System.out.println("Nhập STK gồm 6 chữ số: ");
                String accountNumber = scanner.nextLine().trim();

                // Kiểm tra STK tồn tại
                for(Customer cus: activeBank.getCustomers()) {
                    if(cus.getCustomerId().equals(CUSTOMER_ID)) {
                        if(cus.isAccountExisted(accountNumber)) {
                            throw new Exception("STK đã tồn tại!");
                        }
                    }
                }

                // Set STK
                savingsAccount.setAccountNumber(accountNumber);

                // Thoát vòng lặp khi accountNumber đã set
                break;

            } catch (Exception e) {
                // Bắt lỗi từ hàm setAccountNumber
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(Asm02.handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }

        // Cài đặt giá trị số dư
        while(true) {
            try {
                System.out.println("Nhập số dư: ");
                double balance = Double.parseDouble(scanner.nextLine());

                // Set số dư
                savingsAccount.setBalance(balance);
                break;
            } catch (NumberFormatException e){
                // Bắt lỗi nhập vào ký tự
                System.out.println("Xin nhập vào số!");

            } catch (Exception e) {

                // Bắt lỗi từ hàm setBalance
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(Asm02.handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }

        return savingsAccount;
    }

    /**
     *  Xử lý nghiệp vụ tài khoản Loan
     *
     * @Param Account
     */
    public static Account LoanAccount() throws Exception {
        Account loansAccount = new LoansAccount();

        // Cài đặt giá trị số tài khoản
        while(true) {
            try {
                System.out.println("Nhập STK gồm 6 chữ số: ");
                String accountNumber = scanner.nextLine().trim();

                // Kiểm tra STK tồn tại
                for(Customer cus: activeBank.getCustomers()) {
                    if(cus.getCustomerId().equals(CUSTOMER_ID)) {
                        if(cus.isAccountExisted(accountNumber)) {
                            throw new Exception("STK đã tồn tại!");
                        }
                    }
                }

                // Set STK
                loansAccount.setAccountNumber(accountNumber);

                // Thoát vòng lặp khi accountNumber đã set
                break;

            } catch (Exception e) {
                // Bắt lỗi từ hàm setAccountNumber
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(Asm02.handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }
        return loansAccount;
    }

    /**
     *  Xử lý nghiệp vụ rút tiền
     *
     * @SAVINGS
     * Số tiền rút phải lớn hơn hoặc bằng 50.000đ
     * Số tiền 1 lần rút không được quá 5.000.000đ đối với tài khoản thường, và không giới hạn đối với tài khoản Premium.
     * Số dư còn lại sau khi rút phải lớn hơn hoặc bằng 50.000đ
     * Số tiền rút phải là bội số của 10.000đ
     *
     * @LOAN
     * Hạn mức không được qúa giới hạn 100.000.000đ
     * Phí cho mỗi lần giao dịch là 0.05 trên số tiền giao dịch đối với tài khoản thường và 0.01 trên số tiền giao dịch đối với tài khoản Premium.
     * Hạn mức còn lại sau khi rút tiền không được nhỏ hơn 50.000đ
     * Gọi làm log để in ra biên lai theo từng loại tài khoản mỗi khi rút tiền thành công. Định dạng theo mẫu dưới đây.
     */
    public static void withdraw() throws Exception {
        while(true) {
            try {
                System.out.println("Nhâp số tài khoản: ");
                String accountNumber = scanner.nextLine();

                System.out.println("Nhập số tiền rút: ");
                double amount = Double.parseDouble(scanner.nextLine());

                activeBank.withdrawMoney(CUSTOMER_ID, accountNumber, amount);

                if (Asm02.handleOptionsAgain(0)) break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (Asm02.handleOptionsAgain(0)) break;
            }
        }
    }
}
