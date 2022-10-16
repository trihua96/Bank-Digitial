package vn.funix.fx16573.java.asm02.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Bank {
    private String id;
    private List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
    }

    // Hàm thêm khách hàng
    public void addCustomer(Customer newCustomer) throws Exception {

        // Khách hàng đã tồn tại
        if (isCustomerExisted(newCustomer.getCustomerId())) {
            // Ném lỗi về hàm options trong cùng hàm main
            throw new Exception("Khách hàng đã tồn tại!");
        }

        // Thêm khi chưa có khách hàng
        this.customers.add(newCustomer);
        System.out.println("Thêm khách hàng thành công!");
    }

    // Hàm thềm tài khoản
    public void addAccount(String customerId, Account account) throws Exception {
        try {
            for (Customer cus : customers) {
                // Check ID bằng với trong List customer
                if (cus.getCustomerId().equals(customerId)) {
                    // Thêm tài khoản vào ID đó
                    cus.addAccount(account);
                    System.out.println("Thêm TK thành công!");
                    break;
                }
            }
        }catch (Exception e) {
            // Nhận lỗi từ hàm addAccount trong file Customer
            System.out.println(e.getMessage());
        }
    }

    // Hàm hiển thị khách hàng
    public void displayCustomers() {

        if(customers.isEmpty()) {
            // Check List customer = null
            System.out.println("Chưa có khách hàng nào hết...");
        } else {
            // In ra toàn bộ khách hàng
            for(Customer customer : customers) {
                customer.displayInformation();
            }
        }
    }

    // Hàm tìm kiếm theo CCCD
    public void searchCustomerByCCCD(String customerId) throws Exception {
        // Check ID có tồn tại
        if(isCustomerExisted(customerId)) {
            for (Customer cus : getCustomers()) {
                // Check ID bằng nhau
                if (cus.getCustomerId().equals(customerId)) {
                    // Hiển thị khách hàng
                    cus.displayInformation();
                    break;
                }
            }
        } else {
            // Ném lỗi về hàm options cùng hàm main để chọn nhập lại hoặc không
            throw new Exception("Không tìm thấy khách hàng với CCCD: " + customerId);
        }
    }

    // Hàm tìm kiếm theo tên
    public void searchCustomerByName(String name) throws Exception {

        boolean isSearchCustomer = false;
        for(Customer customer : customers) {
            // Check tên bằng nhau
            if(customer.getName().contains(name)) {
                // Hiển thị khách hàng
                customer.displayInformation();
                isSearchCustomer = true;
            }
        }
        if(!isSearchCustomer) {
            throw new Exception("Không tìm thấy khách hàng với tên: " + name);
        }
    }

    // Hàm check khách hàng đã tồn tại
    public boolean isCustomerExisted(String customerId) {
        for(Customer customer : customers) {
            if(customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    // Lấy danh sách khách hàng
    public List<Customer> getCustomers() {
        return customers;
    }

    // Lấy id ngẫu nhiên

    public String getId() {
        this.id = String.valueOf(UUID.randomUUID());
        return this.id;
    }

    // Đổi đơn vị tiền tệ sang VN
    public static DecimalFormat formatCurrency() {
        Locale localeVN = new Locale("vi","VN");
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setGroupingSeparator(',');
        formatSymbols.setCurrencySymbol("đ");
        DecimalFormat vn = (DecimalFormat) DecimalFormat.getCurrencyInstance(localeVN);
        vn.setDecimalFormatSymbols(formatSymbols);
        return vn;
    }
}
