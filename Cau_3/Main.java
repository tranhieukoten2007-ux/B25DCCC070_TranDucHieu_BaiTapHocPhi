import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

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

class QuanLyHocPhi {
    private List<SinhVien> ds = new ArrayList<>();
    private Set<String> daCo = new HashSet<>();

    public void them(String line) throws DuLieuKhongHopLeException {
        String[] p = line.trim().split("\\s+");
        String he = p[0];
        String ma = p[1];
        String tcStr = p[2];

        if (!he.equals("CQ") && !he.equals("CLC") && !he.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        }
        int tc;
        try {
            tc = Integer.parseInt(tcStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong phai so nguyen");
        }
        if (tc < 1 || tc > 30) {
            throw new DuLieuKhongHopLeException("So tin chi ngoai doan [1, 30]");
        }
        if (daCo.contains(ma)) {
            throw new DuLieuKhongHopLeException("Trung ma sinh vien");
        }

        SinhVien sv;
        switch (he) {
            case "CQ":  sv = new ChinhQuy(ma, tc); break;
            case "CLC": sv = new ChatLuongCao(ma, tc); break;
            default:    sv = new LienThong(ma, tc); break;
        }
        ds.add(sv);
        daCo.add(ma);
    }

    public void inKetQua() {
        ds.sort((a, b) -> {
            int c = Long.compare(b.hocPhi(), a.hocPhi());
            if (c != 0) return c;
            return a.getMa().compareTo(b.getMa());
        });
        long tong = 0;
        for (SinhVien sv : ds) {
            System.out.println(sv);
            tong += sv.hocPhi();
        }
        System.out.println("TONG " + tong);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            try {
                ql.them(line);
            } catch (DuLieuKhongHopLeException e) {
                String[] p = line.trim().split("\\s+");
                System.out.println("INVALID " + p[1]);
            }
        }
        ql.inKetQua();
        sc.close();
    }
}