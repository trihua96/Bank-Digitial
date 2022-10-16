package vn.funix.fx16573.java.asm02.models;

import java.util.Scanner;
import java.util.regex.Pattern;

public class User {
    private  static Scanner sc = new Scanner(System.in);
    private static final int numberOfCharacter = 6;
    private String name;
    private String customerId;

    public User() {
    }

    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        boolean isNameCustomer = true;

        // Kiem tra ten nhap vao la chuoi rong
        if(name.isEmpty()) {
            throw new Exception("Xin nhập tên, không được để trống!");
        }

        // Kiem tra ten khach hang nhap vao la so
        for (int i = 0; i < name.length(); i++) {
//          if (name.charAt(i) >= '0' && name.charAt(i) <= '9') {
            Pattern p = Pattern.compile("^[a-zA-z ]+$");
            if (!p.matcher(name).find()) {
                isNameCustomer = false;
                break;
            }
        }

        if(!isNameCustomer) {
            throw new Exception("Tên chỉ chứa ký tự!");
        } else {
            this.name = name;
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) throws Exception {
        // Tạo đối tượng class ValidateIdNumber
        ValidateIdNumber isIdNumber = new ValidateIdNumber();

        // Kiểm tra ID nhập vào là chuỗi rỗng
        if(customerId.isEmpty()) {
            throw new Exception("CCCD không được để trống!");
        }

        // Kiểm tra ID nhập vào
        if(isIdNumber.validateCitizenID(customerId)) {
            // Hợp lệ
            this.customerId = customerId;
        } else {
            // Lỗi ném về hàm options cùng hàm main
            throw new Exception("Số CCCD không hợp lệ!");
        }
    }

}