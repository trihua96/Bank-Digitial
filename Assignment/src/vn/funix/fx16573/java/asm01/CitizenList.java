package vn.funix.fx16573.java.asm01;

import java.util.ArrayList;
import java.util.Arrays;

public class CitizenList {
    // Thêm 63 tỉnh thành vào mảng
    public static ArrayList<String> provinceList = new ArrayList<>(
            Arrays.asList("Ha Noi", "Ha Giang", "Cao Bang", "Bac Kan", "Tuyen Quang", "Lao Cai", "Dien Bien", "Lai Chau", "Son La", "Yen Bai", "Hoa Binh", "Thai Nguyen", "Lang Son", "Quang Ninh", "Bac Giang", "Phu Tho", "Vinh Phuc", "Bac Ninh", "Hai Duong", "Hai Phong", "Hung Yen", "Thai Binh", "Ha Nam", "Nam Dinh", "Ninh Binh", "Thanh Hoa", "Nghe An", "Ha Tinh", "Quang Binình", "Quang Tri", "Thua Thien Hue", "Da Nang", "Quang Nam", "Quang Ngai", "Binh Dinh", "Phu Yen", "Khanh Hoa", "Ninh Thuan", "Binh Thuan", "Kon Tum", "Gia Lai", "Dak Lak", "Dak Nong", "Lam Dong" , "Binh Phuoc", "Tay Ninh", "Binh Duong", "Dong Nai", "Ba Ria- Vung Tau", "Ho Chi Minh", "Long An", "Tien Giang", "Ben Tre", "Tra Vinh", "Vinh Long", "Dong Thap", "An Giang", "Kien Giang", "Can Tho", "Hau Giang", "Soc Trang", "Bac Lieu", "Ca Mau"));

    // Thêm các số trên căn cước vào mảng
    public static ArrayList<String> numberIDList = new ArrayList<>(Arrays.asList("001", "002", "004", "006", "008", "010", "011", "012", "014", "015", "017", "019", "020", "022", "024", "025", "026", "027", "030", "031", "033", "034", "035", "036", "037", "038", "040", "042", "044", "045", "046", "048", "049", "051", "052", "054", "056", "058", "060", "062", "064", "066", "067", "068", "070", "072", "074", "075", "077", "079", "080", "082", "083", "084", "086", "087", "089", "091", "092", "093", "094", "095", "096"));



    // Tìm index bên trong mảng chứa số căn cước
    public static int findIndex(String numberID) {
        return numberIDList.indexOf(numberID);
    }
}
