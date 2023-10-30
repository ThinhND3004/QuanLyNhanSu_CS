package cb.models;

public class GiamDoc extends NhanSu{
    private double coPhanCongTy;

    public void display()
    {
        System.out.println("Giám đốc: ");
        super.display();
        System.out.println("Cổ phần công ty: " + coPhanCongTy);
    }

    public GiamDoc(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec, double coPhanCongTy) {
        super(hoVaTen, maSO, soDienThoai, soNgayLamViec);
        this.coPhanCongTy = coPhanCongTy;
        this.setLuongMotNgay(300);
    }

    public GiamDoc(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec, double luongMotNgay, double coPhanCongTy) {
        super(hoVaTen, maSO, soDienThoai, soNgayLamViec, luongMotNgay);
        this.coPhanCongTy = coPhanCongTy;
    }

    public GiamDoc(double coPhanCongTy) {
        this.coPhanCongTy = coPhanCongTy;
    }

    public double getCoPhanCongTy() {
        return coPhanCongTy;
    }

    public void setCoPhanCongTy(double coPhanCongTy) {
        this.coPhanCongTy = coPhanCongTy;
    }

    @Override
    public double tinhLuong() {
        return this.getLuongMotNgay() * this.getSoNgayLamViec();
    }
}
