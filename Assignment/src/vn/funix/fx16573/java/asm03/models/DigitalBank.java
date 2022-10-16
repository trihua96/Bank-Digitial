package vn.funix.fx16573.java.asm03.models;

import vn.funix.fx16573.java.asm02.models.Account;
import vn.funix.fx16573.java.asm02.models.Bank;
import vn.funix.fx16573.java.asm02.models.Customer;

public class DigitalBank extends Bank {

    // Hàm lấy dữ liệu khách hàng với ID tồn tại
    /**
     * @param customerId
     * */
    public Customer getCustomerById(String customerId) {
        for(Customer customer : super.getCustomers()) {
            if(customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    // Hàm rút tiền theo ID
    /**
     *  ID tồn tại thì gọi hàm withdraw của đối tượng khách hàng tìm được.
     * */
    public void withdrawMoney(String customerId, String accountNumber, double amount) throws Exception {

        // Kiểm tra ID
        for(Customer customer : getCustomers()) {
            if(customer.getCustomerId().equals(customerId) && customer instanceof DigitalCustomer) {
                ((DigitalCustomer)customer).withdrawMoney(accountNumber,amount, getId());
            }
        }
    }

    // Hiển thị lịch sử giao dịch
    /**
     * @param customerId: kiểm tra id tồn tại
     *                  Nếu tồn tại sẽ in khách hàng đó
     * */
    public void viewTransactionHistory(String customerId) {
        Customer customer = getCustomerById(customerId);
        if(customer != null) {
            System.out.println(Utils.getDivider());
            System.out.printf("| LỊCH SỬ GIAO DỊCH" + "%24s%n","|");
            System.out.println(Utils.getDivider());
            // Hiển thị khách hàng
            customer.displayInformation();

            // Hiển thị tài khoản của khách hàng
            for(Account account : customer.getAccounts()) {
                /**
                 * In ra thông tin tài khoản gồm:
                 * STK    | amount  |        Date         | Mã giao dịch(id random)
                 * 123123 |-50,000đ | 06/09/2022 16:35:59 | 05954c60-7f4f-4eae-849a-647ba4e80240
                 * */
                account.viewTransactionHistory();
            }
            System.out.println(Utils.getDivider());
        }
    }

    // Hàm thêm khách hàng
    /**
     * @param newCustomer
     * tham số khách hàng để thêm vào danh sách
     * */
    @Override
    public void addCustomer(Customer newCustomer) throws Exception {
        super.getCustomers().add(newCustomer);
    }

    // Hàm thêm tài khoản
    /**
     * @param customerId, account
     *  Thêm tài khoản với id nhập vào nếu tồn tại
     * */
    @Override
    public void addAccount(String customerId, Account account) throws Exception {
        for (Customer cus : super.getCustomers()) {
            // Check ID bằng với trong List customer
            if (cus.getCustomerId().equals(customerId)) {
                // Thêm tài khoản vào ID đó
                cus.addAccount(account);
                System.out.println("Thêm TK thành công!");
                break;
            }
        }
    }
}
