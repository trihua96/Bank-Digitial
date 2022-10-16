package vn.funix.fx16573.java.asm01;

import java.util.Scanner;

public class Asm01 {
        private  static final String AUTHOR = "FX16573";
        private  static final String VERSION = "@V1.0.0";
        private static Scanner sc = new Scanner(System.in);

        // Biến phạm vi của chuỗi mã xác minh
        private static final int numberOfCharacter = 6;

        public static void main(String[] args) {

            int choice = 0;
            boolean quit = false;

            while(!quit) {
                try {
                    // Hiển thị menu chính
                    printInstructionMenu();
                    // Cho người dùng nhập lựa chọn
                    System.out.print("Nhap lua chon: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    // Xử lý lựa chọn
                    switch (choice) {
                        case 0:
                            quit = true;
                            break;
                        case 1:
                            validateVerifyCode();
                            break;
                        default:
                            System.out.println("Lua chon khong dung. Vui long nhap lai.");
                            break;
                    }
                } catch (Exception e) {
                    // Xử lý ngoại lệ nhập vào ký tự
                    System.out.println("Xin hay nhap vao so!");
                    sc.nextLine();
                }
            }
//        }

        }

        // Menu chính
        public static void printInstructionMenu() {
            System.out.println("\n+----------+------------------------+---------+");
            System.out.println("| NGAN HANG SO |       " + AUTHOR + VERSION + "        |");
            System.out.println("+----------+------------------------+---------+");
            System.out.println("| 1. Nhap CCCD                                |");
            System.out.println("| 0. Thoat                                    |");
            System.out.println("+----------+------------------------+---------+");
        }

        // Menu chức năng
        public static void displaySubMenu() {
            System.out.println("\t| 1. Kiem tra noi sinh");
            System.out.println("\t| 2. Kiem tra tuoi, gioi tinh ");
            System.out.println("\t| 3. Kiem tra so ngau nhien");
            System.out.println("\t| 0. Thoat");
        }

        // Hiển thị Tỉnh thành
        public static void displayProvince(String numberProvince) {
            // Lấy chỉ mục từ mảng numberIDList các số căn cước tỉnh
            int valueIndex = CitizenList.findIndex(numberProvince);

            // Hiển thị tỉnh thành từ danh sách mảng provinceList
            System.out.println("Noi Sinh: " + CitizenList.provinceList.get(valueIndex));
        }

        /*
            Thế kỷ 20 (từ năm 1900 đến hết năm 1999): Nam 0, nữ 1.
            Thế kỷ 21 (từ năm 2000 đến hết năm 2099): Nam 2, nữ 3.
            Thế kỷ 22 (từ năm 2100 đến hết năm 2199): Nam 4, nữ 5.
            Thế kỷ 23 (từ năm 2200 đến hết năm 2299): Nam 6, nữ 7.
            Thế kỷ 24 (từ năm 2300 đến hết năm 2399): Nam 8, nữ 9.
        */
        // Hiển thị giới tính và năm sinh
        public static String displaySexAndBirthYear(String numberSex, String numberBirthYear) {
            int[] centuries= {20, 21, 22, 23, 24};
            int getCentury = 0;
            String[] sex = {"Nam", "Nu"};

            if(numberSex.equals("0") || numberSex.equals("1")) {
                getCentury = centuries[0];
            } else if(numberSex.equals("2") || numberSex.equals("3")) {
                getCentury = centuries[1];
            }else if(numberSex.equals("4") || numberSex.equals("5")) {
                getCentury = centuries[2];
            }else if(numberSex.equals("6") || numberSex.equals("7")) {
                getCentury = centuries[3];
            } else if(numberSex.equals("8") || numberSex.equals("9")) {
                getCentury = centuries[4];
            }

            return "Gioi tinh: " + (Integer.parseInt(numberSex) % 2 == 0 ? sex[0] : sex[1]) + " | " + ((getCentury - 1) + numberBirthYear);
        }

        // Hiển thị số ngẫu nhiên
        public static void displayRandomNumber(String randNumber) {
            System.out.println("So ngau nhien: " + randNumber);
        }

        // Hàm boolean xác minh số căn cước
        public static boolean validateNumberProvince(String numberProvince) {
            // Lấy chỉ mục từ mảng numberIDList các số căn cước tỉnh
            int valueIndex = CitizenList.findIndex(numberProvince);

            if(valueIndex >= 0) {
                // Từ 0 - 62
                return true;
            }
            return false;
        }

        //  Kiểm tra mã xác minh hợp lệ
        public static void validateVerifyCode() {
            boolean isVerify = false;
            while(!isVerify) {
                String verifyCode = GeneratorRandom.randomAlphaNumeric(numberOfCharacter);

                // Hiển thị mã xác minh và yêu cầu nhập mã
                System.out.println("Nhap ma xac thuc: " + verifyCode);
                String codeInput = sc.nextLine();

                // So sánh 2 chuỗi = nhau không
                if (codeInput.equals(verifyCode)) {
                    validateCitizenID();
                    isVerify = true;
                } else {
                    System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
                }
            }
        }

        // Kiểm tra Căn cước công dân hợp lệ
        public static void validateCitizenID() {
            boolean isVerify = false; // Biến để nhập lại khi người dùng sai
            while(!isVerify) {
                System.out.print("Vui long nhap so CCCD: ");
                String ID = sc.nextLine().trim();

                boolean flag = false; // biến flag kiểm tra số hợp lệ
                // Kiểm tra độ dài chuỗi = 12
                if(ID.length() == 12) {

                    for(int i = 0 ; i< ID.length(); i++) {
                        // Kiểm tra ký tự số nằm trong [0-9]
                        if((ID.charAt(i) >= '0') && (ID.charAt(i) <= '9')) {
                            flag = true;
                        } else {
                            // Thoát ngay khi có bất kỳ ký tự nào sai
                            break;
                        }
                    }

                    // Kiểm tra flag
                    if(!flag) {
                        isVerify = printNotValid();
                    } else {
                        // Số CCCD hợp lệ [0-9] thì kiểm tra tiếp các số tỉnh thành, giới tính
                        // Lấy giá trị 3 số đầu của tỉnh và chuyển đổi thành String
                        String numberProvince = String.valueOf(ID.charAt(0)) +  ID.charAt(1) + ID.charAt(2);

                        // Lấy giá tri giới tính
                        String numberSex = String.valueOf(ID.charAt(3));

                        // Lấy giá trị năm sinh
                        String numberBirthYear = String.valueOf(ID.charAt(4)) + ID.charAt(5);

                        // Lấy giá trị số ngẫu nhiên
                        String randNumber = ID.substring(6);

                        boolean isNumberProvince = validateNumberProvince(numberProvince);

                        if(isNumberProvince) {
                            // Kiểm tra số căn cước hợp lệ
                            // Hiển thị menu chức năng lựa chọn thông tin căn cước
                            displaySubMenu();

                            // Xử lý nhập lựa chọn các chức năng
                            handleOptions(numberProvince, numberSex, numberBirthYear, randNumber);
                        } else {
                            // Kiểm tra không hợp lệ
                            isVerify = printNotValid();
                        }

                    }
                } else {
                    isVerify = printNotValid();
                }
            }
        }

        // Hàm xử lý chọn chức năng và hiển thị
        public static void handleOptions(String numberProvince, String numberSex, String numberBirthYear, String randNumber) {
            boolean isQuitSubMenu = false;
            int optionInput = 0;
            while (!isQuitSubMenu) {
                try {
                    System.out.print("Chon chuc nang: ");
                    optionInput = sc.nextInt();
                    sc.nextLine();

                    switch (optionInput) {
                        case 1:
                            // Hiển thị tỉnh
                            displayProvince(numberProvince);
                            break;
                        case 2:
                            // Hiển thị giới tính, tuổi
                            String sexAge = displaySexAndBirthYear(numberSex, numberBirthYear);
                            System.out.println(sexAge);
                            break;
                        case 3:
                            // Hiển thị số ngẫu nhiên
                            displayRandomNumber(randNumber);
                            break;
                        case 0:
                            // Thoat
                            isQuitSubMenu = true;
                            break;
                        default:
                            System.out.println("Lua chon sai. Vui long nhap lai!");
                            break;
                    }
                } catch(Exception e) {
                    System.out.println("Xin hay nhap vao so!");
                    sc.nextLine();
                }

            }
        }

        // Hàm in không hợp lệ
        public static boolean printNotValid() {
            boolean isVerify = false;
            System.out.println("So CCCD khong hop le. Vui long enter de nhap lai hoac ‘No’ de thoat");
            String quit = sc.nextLine();

            // Kiêm tra nguời dùng thoát hay không
            if(quit.equals("no") || quit.equals("No")) {
                isVerify = true; // Biến thoát khỏi vòng lặp
            }
            return isVerify;
        }

}

