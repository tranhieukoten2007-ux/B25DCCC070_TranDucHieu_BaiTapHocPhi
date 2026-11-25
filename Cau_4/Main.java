import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return hocPhi * 50 / 100; }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return hocPhi * 70 / 100; }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return Math.min(hocPhi, 3000000L); }
}

abstract class SinhVien {
    protected static final long DON_GIA = 450000L;
    protected String maHe;
    protected String ma;
    protected int tinChi;
    protected MienGiam mienGiam; // co the null

    public SinhVien(String maHe, String ma, int tinChi) {
        this.maHe = maHe;
        this.ma = ma;
        this.tinChi = tinChi;
    }

    public abstract long hocPhi();

    public void setMienGiam(MienGiam mg) { this.mienGiam = mg; }

    public long soTienGiam() {
        return mienGiam == null ? 0 : mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() { return hocPhi() - soTienGiam(); }

    public String getMaHe() { return maHe; }
    public String getMa() { return ma; }

    @Override
    public String toString() {
        return ma + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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
        Map<String, SinhVien> map = new HashMap<>();
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
            map.put(ma, sv);
        }

        int m = Integer.parseInt(sc.nextLine().trim());
        List<String> notFound = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String ma = p[0];
            String cs = p[1];
            SinhVien sv = map.get(ma);
            if (sv == null) {
                notFound.add(ma);
                continue;
            }
            switch (cs) {
                case "CS": sv.setMienGiam(new ConChinhSach()); break;
                case "HN": sv.setMienGiam(new HoNgheo()); break;
                case "HB": sv.setMienGiam(new HocBong()); break;
                default: break;
            }
        }

        for (String ma : notFound) {
            System.out.println("NOT FOUND " + ma);
        }
        for (SinhVien sv : ds) {
            System.out.println(sv);
        }

        String[] heList = {"CQ", "CLC", "LT"};
        for (String he : heList) {
            int dem = 0;
            long tong = 0;
            for (SinhVien sv : ds) {
                if (sv.getMaHe().equals(he)) {
                    dem++;
                    tong += sv.phaiNop();
                }
            }
            if (dem > 0) {
                System.out.println(he + " " + dem + " " + tong);
            }
        }
        sc.close();
    }
}