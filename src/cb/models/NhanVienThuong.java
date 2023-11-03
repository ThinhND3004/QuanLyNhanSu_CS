package cb.models;

public class NhanVienThuong extends NhanSu{
    private String truongPhongQuanLy;

    public NhanVienThuong(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec) {
        super(hoVaTen, maSO, soDienThoai, soNgayLamViec);
        this.setLuongMotNgay(100);
        this.truongPhongQuanLy = "Chưa có trưởng phòng quản lí";
    }

    public void display()
    {
        System.out.println("Nhân viên thường:");
        super.display();
        System.out.println("Trưởng phòng quản lý: " + truongPhongQuanLy);
    }

    public String getTruongPhongQuanLy() {
        return truongPhongQuanLy;
    }

    public void setTruongPhongQuanLy(String truongPhongQuanLy) {
        this.truongPhongQuanLy = truongPhongQuanLy;
    }

    public NhanVienThuong(String truongPhongQuanLy) {
        this.truongPhongQuanLy = truongPhongQuanLy;
    }

    @Override
    public double tinhLuong() {
        return this.getLuongMotNgay() * this.getSoNgayLamViec();
    }
}
