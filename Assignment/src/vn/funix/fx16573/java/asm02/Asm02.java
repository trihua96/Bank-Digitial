package vn.funix.fx16573.java.asm02;

import vn.funix.fx16573.java.asm02.models.Account;
import vn.funix.fx16573.java.asm02.models.Bank;
import vn.funix.fx16573.java.asm02.models.Customer;

import java.util.Scanner;

public class Asm02 {
    private static final Scanner sc = new Scanner(System.in);
    private static final String AUTHOR = "FX16573";
    private static final String VERSION = "@V2.0.0";

    private static final Bank bank = new Bank();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean inputAgain = false; // Tạo biến tiếp tục chọn chức năng hoặc không
        while(!inputAgain) {
            while (true) {
                // Hiển  thị menu chức năng
                printInstructionMenu();
                try {
                    System.out.println("Chọn chức năng: ");
                    int numberInput = Integer.parseInt(sc.nextLine());

                    options(numberInput);

                    // Bằng 0: Thoát. Gán giá trị để thoát vòng lặp
                    if(numberInput == 0) inputAgain = true;

                    // Khi lựa chọn xong thì thoát vòng lặp true
                    if (numberInput >= 0 && numberInput <= 5) {
                        break;
                    }

                } catch (Exception e) {
                    if(e.getMessage() == null) {
                        inputAgain = handleOptionsAgain(1);
                        // Thoát khỏi ứng dụng
                        if(inputAgain) break;
                        // In ra nếu người dùng tiếp tục chọn chức năng
                        System.out.println("\t\t--- Mời bạn chọn chức năng ---");
                    } else {
                        System.out.println("Nhập số để chọn chức năng!");
                    }
                }
            }

            // Đã sử dụng chức năng xong
            if(!inputAgain) {
                // Gọi hàm cho lựa chọn để tiếp tục hoặc thoát app
                inputAgain = handleOptionsAgain(1);
            }
        }


    }

    // Hàm hiển thị menu chính
    public static void printInstructionMenu() {
        System.out.println("\n+----------+------------------------+---------+");
        System.out.println("| NGAN HANG SO |       " + AUTHOR + VERSION + "         |");
        System.out.println("+----------+------------------------+---------+");
        System.out.println("| 1. Thêm khách hàng                          |");
        System.out.println("| 2. Thêm tài khoản cho khách hàng            |");
        System.out.println("| 3. Hiển thị danh sách khách hàng            |");
        System.out.println("| 4. Tìm theo CCCD                            |");
        System.out.println("| 5. Tìm theo tên khách hàng                  |");
        System.out.println("| 0. Thoát                                    |");
        System.out.println("+----------+------------------------+---------+");
    }

     /**
     *   Hàm xử lý chức năng chọn lại
         numberOrderMenu là tham số để hiển thị lựa chọn:
             1. Chọn chức năng menu chính.
             0. Chọn lại để nhập lại.
     **/
    public static boolean handleOptionsAgain(int numberOrderMenu) {

        boolean isAgain = false;
        while (true) {
            try {
                System.out.println("\nBạn có muốn " + (numberOrderMenu == 1 ? "'Chọn Chức Năng' hay 'Thoát'." : "Nhập lại?") + " Nhập: Yes/No (Y/N).");
                String continute = sc.nextLine().trim();

                if(continute.isEmpty()) {
                    System.out.println("--- Xin đừng để trống! Nhập Yes/No ---");
                } else {
                    if (continute.equals("N") || continute.equals("n") || continute.equals("No") || continute.equals("no")) {
                        isAgain = true;
                        break;
                    } else if (continute.equals("Y") || continute.equals("y") || continute.equals("Yes") || continute.equals("yes")) {
                        isAgain = false;
                        break;
                    } else {
                        System.out.println("--- Nhập Yes/No. Xin nhập lại! ---");
                    }
                }

            } catch (Exception e) {
                System.out.println("--- Nhập Yes/No. Xin nhập lại! ---");
                sc.nextLine();
            }
        }
        return isAgain;
    }

    // Hàm xử lý chức năng chính
    public static void options(int numberInput) throws Exception {
        boolean check = false;
        while(true) {
            // Vòng lặp cho người dùng nhập lại khi sai
            try {
                switch (numberInput) {
                    case 1:
                        // Thêm khách hàng
                        bank.addCustomer(addCustomer());
                        check = true;
                        break;
                    case 2:
                        // Thêm tài khoản
                        String customerId = customerId();
                        bank.addAccount(customerId, addAccount(customerId));
                        check = true;
                        break;
                    case 3:
                        // Hiển thị danh sách khách hàng
                        bank.displayCustomers();
                        check = true;
                        break;
                    case 4:
                        // Tìm theo CCCD
                        bank.searchCustomerByCCCD(inputSearchCustomerById());
                        check = true;
                        break;
                    case 5:
                        // Tìm theo tên khách hàng
                        /**
                         * Nhập từ khoá tìm theo tên khách hàng, yêu cầu tìm gần đúng.
                         * */
                        bank.searchCustomerByName(inputSearchCustomerByName());
                        check = true;
                        break;
                    case 0:
                        // Thoat
                        check = true;
                        break;
                    default:
                        System.out.println("Lựa chọn sai. Xin nhập lại!");
                        check = true;
                        break;
                }
            } catch (Exception e) {
                // Cacth lỗi trong bank ném ra

                 /**
                  * Bank ném null về từ các hàm đã gọi
                  * Null: Người dùng không nhập lại và thoát
                    Ném null về cho hàm main để chọn chức năng
                  */
                if(e.getMessage() == null) throw new Exception();

                // Thông báo lỗi dữ liệu nhập trong bank
                System.out.println(e.getMessage());

                // Cho nhập lại tại chức năng hiện tại
                if (handleOptionsAgain(0)) break;
            }
            // Thoát sau khi chức năng hoàn thành
            if(check) break;
        }

    }

    // Hàm cài đặt giá trị cho khách hàng
    public static Customer addCustomer() throws Exception {
        Customer newCustomer = new Customer();

        while(true) {
            try {
                System.out.println("Nhập tên khách hàng: ");
                String nameCustomer = sc.nextLine();

                // Gọi hàm cài đặt tên
                newCustomer.setName(nameCustomer);

                // Thoát khỏi vòng lặp nếu tên nhập vào đúng
                break;
            } catch (Exception e) {
                // Bắt lỗi tên nhập vào sai
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }


        while(true) {
            try {
                System.out.println("Nhập số CCCD: ");
                String idNumber = sc.nextLine().trim();

                newCustomer.setCustomerId(idNumber);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }

        return newCustomer;
    }

    // Hàm trả về ID hợp lệ của khách hàng
    public static String customerId() throws Exception {

        // Biến lưu trữ chuỗi customerId
        String idNumber = "";

        while(true) {
            try {
                System.out.println("Nhập CCCD khách hàng: ");
                String customerId = sc.nextLine().trim();

                if(customerId.isEmpty()) {
                    throw new Exception("Không được để trống!");
                }

                // Lỗi nếu mã ID nhập vào không tồn tại
                if (!bank.isCustomerExisted(customerId)) {
                    throw new Exception("Khách hàng không tồn tại!");
                }

                // Nếu ko lỗi thì gán giá trị để trả về chuỗi id
                idNumber = customerId;
                // Thoát vòng lặp cho nhập lại
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());

                // Xử lý cho nhập lại
                if(handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }

        return idNumber;
    }

    /*
    *   Hàm thêm tài khoản
    *   trả về một account
    *   truyển vào bank.addAccount(customerId, account)
    * */
    public static Account addAccount(String customerId) throws Exception{
        Account account = new Account();

        // Cài đặt giá trị số tài khoản
        while(true) {
            try {
                System.out.println("Nhập STK gồm 6 chữ số: ");
                String accountNumber = sc.nextLine().trim();

                // Kiểm tra STK tồn tại
                for(Customer cus: bank.getCustomers()) {
                    if(cus.getCustomerId().equals(customerId)) {
                        if(cus.isAccountExisted(accountNumber)) {
                            throw new Exception("STK đã tồn tại!");
                        }
                    }
                }

                // Set STK
                account.setAccountNumber(accountNumber);

                // Thoát vòng lặp khi accountNumber đã set
                break;

            } catch (Exception e) {
                // Bắt lỗi từ hàm setAccountNumber
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }

        // Cài đặt giá trị số dư
        while(true) {
            try {
                System.out.println("Nhập số dư: ");
                double balance = Double.parseDouble(sc.nextLine());

                // Set số dư
                account.setBalance(balance);
                break;
            } catch (NumberFormatException e){
                // Bắt lỗi nhập vào ký tự
                System.out.println("Xin nhập vào số!");

            } catch (Exception e) {

                // Bắt lỗi từ hàm setBalance
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }

        return account;
    }

    // Hàm tìm kiếm khách hàng theo CCCD
    public static String inputSearchCustomerById() throws Exception {
        // Biến lưu trữ tạm thời chuỗi ID tìm kiếm
        String searchNumberIdStore = "";
        while(true) {
            try {
                // Nhập ID khách hàng
                System.out.println("\nNhập CCCD khách hàng cần tìm: ");
                String searchNumberId = sc.nextLine();

                if (searchNumberId.isEmpty()) {
                    throw new Exception("Xin đừng để trống!");
                }

                // Gán giá trị để trả về và thoát vòng lặp
                searchNumberIdStore = searchNumberId;
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());

                // Gọi hàm cho lựa chọn nhập lại hoặc không
                // Nếu lựa chọn không thì trả về true và thoát vòng lặp
                if(handleOptionsAgain(0)) {
                    // Ném null về hàm options
                    throw new Exception();
                }
            }
        }
        return searchNumberIdStore;
    }

    // Hàm tìm kiếm khách hàng theo tên
    public static String inputSearchCustomerByName() {

        // Biến lữu trữ dữ liệu nhập để trả về
        String searchNameStore = "";
        while(true) {
            try {
                // Nhập tên cần tìm
                System.out.println("Nhập tên khách hàng cần tìm: ");
                String searchName = sc.nextLine();

                // Kiểm tra chuỗi rỗng
                if (searchName.isEmpty()) throw new Exception("Không được để trống!");

                // Gán giá trị để trả về
                searchNameStore = searchName;
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return searchNameStore;
    }

    /**
     * Code đã sai yêu cầu.
     *       !
     *       !
     *      \/
     * ********************/

    /*       public static void addCustomer() {
        Customer customer = new Customer();

        boolean isNameCustomer = false;
        while(true) {
            System.out.println("Nhap ten khach hang: ");
            String nameCustomer = sc.nextLine().trim();

            // Kiem tra ten nhap vao la chuoi rong
            if(nameCustomer.isEmpty()) {
                System.out.println("Xin nhap ten, khong duoc de trong!");
                isNameCustomer = false;
            } else {

                // Kiem tra ten khach hang nhap vao la so
                for (int i = 0; i < nameCustomer.length(); i++) {
                    if (nameCustomer.charAt(i) >= '0' && nameCustomer.charAt(i) <= '9') {
                        System.out.println("Ten phai chua ky tu!");
                        isNameCustomer = false;
                        break;
                    } else {
                        isNameCustomer = true;
                    }
                }
            }

            if(isNameCustomer) {
                customer.setName(nameCustomer);
                break;
            }
        }

        boolean isIdNumber = false;
        while(!isIdNumber) {
            System.out.println("Nhap so CCCD: ");
            String idNumber = sc.nextLine().trim();

            isIdNumber = ValidateIdNumber.validateCitizenID(idNumber);

            if(isIdNumber) {
                boolean cusExisted = bank.isCustomerExisted(idNumber);
                if(cusExisted) {
                    System.out.println("So CCCD da ton tai!");

                    isIdNumber = handleOptionsAgain(0);

                } else {

                        customer.setCustomerId(idNumber);
                        bank.addCustomer(customer);
                        System.out.println("Them thanh cong!");

                }
            } else {
                isIdNumber = handleOptionsAgain(0);
            }
        }
    }*/

    /*public static void validateAddAccount() {
        Account account  = new Account();

        boolean isAccountNumber = false;
        while(!isAccountNumber) {
            System.out.println("Nhap CCCD khach hang: ");
            String customerId = sc.nextLine().trim();

            boolean cusExisted = bank.isCustomerExisted(customerId);
            if(!cusExisted) {
                System.out.println("So CCCD khong ton tai!");
                isAccountNumber = handleOptionsAgain(0);
            } else {
                while(true) {
                    System.out.println("Nhap ma STK gom 6 chu so: ");
                    String accountNumber = sc.nextLine().trim();

                    // Kiểm tra STK phải gồm 6 số
                    if (accountNumber.length() == 6) {
                        for (int i = 0; i < accountNumber.length(); i++) {
                            if ((accountNumber.charAt(i) >= '0') && (accountNumber.charAt(i) <= '9')) {
                                isAccountNumber = true;
                            } else {
                                System.out.println("So tai khoan phai 6 chu so. Xin nhap lai!");
                                isAccountNumber = false;
                                break;
                            }
                        }
                        if (isAccountNumber) {
                            for(Customer cus : bank.getCustomers()) {
                                if (cus.getCustomerId().equals(customerId) && !cus.getAccounts().isEmpty()) {
                                    for(Account acc : cus.getAccounts()) {
                                        if(acc.getAccountNumber().equals(accountNumber)) {
                                            System.out.println("STK da ton tai!");
                                            isAccountNumber = false;
                                            break;
                                        } else {
                                            addAccount(account,accountNumber,cus);
                                            break;
                                        }
                                    }
                                }
                            }
                           if(isAccountNumber) {
                               break;
                           } else {
                               if(handleOptionsAgain(0)) {
                                   isAccountNumber = true;
                                   break;
                               }
                           }
                        }
                    } else {
                        System.out.println("STK phai gom 6 chu so.");
                        if(handleOptionsAgain(0)) {
                           isAccountNumber = true;
                           break;
                        }

                    }
                }
            }


        }
    }*/

    /*public static void addAccount(Account account, String accountNumber, Customer cus) {
        while (true) {
            System.out.println("Nhap so du: ");
            double balance = sc.nextDouble();
            sc.nextLine();

            if (balance >= 50_000) {
                account.setBalance(balance);
                break;
            } else {
                System.out.println("So du phai 50.000 tro len. Xin nhap lai!");
            }
        }
        account.setAccountNumber(accountNumber);
        cus.addAccount(account);
        System.out.println("Them TK thanh cong!");
    }*/
    /*
    *  Tìm thông tin khách hàng theo CCCD
    */

    /*    public static void searchID() {
        boolean isSearchNumber = false;
        while(true) {
            System.out.println("\nNhap CCCD can tim");
            String searchNumber = sc.nextLine();

            if(searchNumber.isEmpty()) {
                System.out.println("Xin dung de trong!");
            } else {

                boolean idExisted = bank.isCustomerExisted(searchNumber);

                if(ValidateIdNumber.validateCitizenID(searchNumber) && idExisted) {
                    for(Customer cus : bank.getCustomers()) {
                        if(cus.getCustomerId().equals(searchNumber)) {
                            cus.displayInformation();
                            break;
                        }
                    }
                    isSearchNumber = handleOptionsAgain(0);
                } else {
                    if(!idExisted)
                        System.out.println("Khach hang khong ton tai!");
                    isSearchNumber = handleOptionsAgain(0);
                }

                if(isSearchNumber) {
                    break;
                }
            }

        }
}*/
}

