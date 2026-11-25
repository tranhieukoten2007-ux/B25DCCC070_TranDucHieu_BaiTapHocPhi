import java.util.*;

class SinhVien {
    private String ma;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String ma, String hoTen, String ngaySinh) {
        this.ma = ma.trim();
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }

    private static String chuanHoaTen(String s) {
        String[] parts = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (p.isEmpty()) continue;
            if (sb.length() > 0) sb.append(' ');
            sb.append(Character.toUpperCase(p.charAt(0)));
            sb.append(p.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    private static String chuanHoaNgay(String s) {
        String[] p = s.trim().split("/");
        int d = Integer.parseInt(p[0].trim());
        int m = Integer.parseInt(p[1].trim());
        int y = Integer.parseInt(p[2].trim());
        return String.format("%02d/%02d/%d", d, m, y);
    }

    @Override
    public String toString() {
        return ma + " " + hoTen + " " + ngaySinh;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ngay = sc.nextLine();
            ds.add(new SinhVien(ma, ten, ngay));
        }
        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
        sc.close();
    }
}