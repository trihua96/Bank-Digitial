package vn.funix.fx16573.java.asm03.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Utils {
    /**
     *  Không cho bất cứ ai khởi tạo
     *  */
    private Utils() {}

    // Hàm in chia hàng
    public static String getDivider() {
        return  "+-------+-------------------------+-------+";
    }

    // Hàm in chia hàng cuối biên lai
    public static String getDividerEnd() {
        String dividerEnd = "\t\t***** XIN CAM ON *****\n+-------+-------------------------+-------+";

        return dividerEnd;
    }

    //Hàm lấy dữ liệu ngày tháng năm
    public static String getDateTime() {
        // Tạo obj Date khu vực hiện tại
        LocalDateTime dateObj = LocalDateTime.now();

        // Format Date
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Lưu Date vào chuỗi
        String formattedDate = dateObj.format(dateFormatObj);

        return formattedDate;
    }

    // Hàm chức năng chuyển đổi số dư sang VN
    public static String formatBalance(double amount) {
        return formatCurrencyToVN().format(amount);
    }

    // Hàm định dạng sang tiền tệ Việt Nam
    public static DecimalFormat formatCurrencyToVN() {
        Locale localeVN = new Locale("vi","VN");
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setGroupingSeparator(',');
        formatSymbols.setCurrencySymbol("đ");
        DecimalFormat vn = (DecimalFormat) DecimalFormat.getCurrencyInstance(localeVN);
        vn.setDecimalFormatSymbols(formatSymbols);
        return vn;
    }
}
