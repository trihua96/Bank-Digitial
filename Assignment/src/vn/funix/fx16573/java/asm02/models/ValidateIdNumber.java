package vn.funix.fx16573.java.asm02.models;

import vn.funix.fx16573.java.asm01.CitizenList;

public class ValidateIdNumber {
    public boolean validateCitizenID(String numberID) {

        // Kiểm tra độ dài chuỗi = 12
        if(numberID.length() == 12) {

            // Số CCCD hợp lệ [0-9] thì kiểm tra tiếp các số tỉnh thành, giới tính
            // Lấy giá trị 3 số đầu của tỉnh và chuyển đổi thành String
            String numberProvince = String.valueOf(numberID.charAt(0)) +  numberID.charAt(1) + numberID.charAt(2);

            // Lấy giá tri giới tính
            String numberSex = String.valueOf(numberID.charAt(3));

            // Lấy giá trị năm sinh
            String numberBirthYear = String.valueOf(numberID.charAt(4)) + numberID.charAt(5);

            // Lấy giá trị số ngẫu nhiên
            String randNumber = numberID.substring(6);

            if(validateNumberProvince(numberProvince)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean validateNumberProvince(String numberProvince) {
        // Lấy chỉ mục từ mảng numberIDList các số căn cước tỉnh
        int valueIndex = CitizenList.findIndex(numberProvince);

        if(valueIndex >= 0) {
            // Từ 0 - 62
            return true;
        }
        return false;
    }

/*    public static boolean printNotValid() {
        System.out.println("So CCCD khong hop le. Vui long nhap lai!");

        return false;
    }*/
}
