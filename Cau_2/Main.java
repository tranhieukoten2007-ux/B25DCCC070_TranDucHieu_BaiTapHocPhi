import java.util.*;

abstract class SinhVien {
    protected static final long DON_GIA = 450000L;
    protected String maHe;
    protected String ma;
    protected int tinChi;

    public SinhVien(String maHe, String ma, int tinChi) {
        this.maHe = maHe;
        this.ma = ma;
        this.tinChi = tinChi;
    }

    public abstract long hocPhi();

    public String getMaHe() { return maHe; }
    public String getMa() { return ma; }

    @Override
    public String toString() {
        return ma + " " + maHe + " " + hocPhi();
    }
}

class ChinhQuy extends SinhVien {
    public ChinhQuy(String ma, int tc) { super("CQ", ma, tc); }

    @Override
    public long hocPhi() { return tinChi * DON_GIA; }
}

class ChatLuongCao extends SinhVien {
    public ChatLuongCao(String ma, int tc) { super("CLC", ma, tc); }

    @Override
    public long hocPhi() { return tinChi * DON_GIA * 3 / 2; }
}

class LienThong extends SinhVien {
    public LienThong(String ma, int tc) { super("LT", ma, tc); }

    @Override
    public long hocPhi() { return tinChi * DON_GIA + 2000000L; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String he = p[0];
            String ma = p[1];
            int tc = Integer.parseInt(p[2]);
            SinhVien sv;
            switch (he) {
                case "CQ":  sv = new ChinhQuy(ma, tc); break;
                case "CLC": sv = new ChatLuongCao(ma, tc); break;
                default:    sv = new LienThong(ma, tc); break;
            }
            ds.add(sv);
        }
        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
        sc.close();
    }
}