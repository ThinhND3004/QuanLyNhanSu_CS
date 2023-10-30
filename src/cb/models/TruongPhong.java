package cb.models;

public class TruongPhong extends NhanSu{
    private int soLuongNhanVienDuoiQuyen;

    public void display()
    {
        System.out.println("Trưởng phòng: ");
        super.display();
        System.out.println("Số lượng nhận viên dưới quyền: " + soLuongNhanVienDuoiQuyen);
    }
    public TruongPhong(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec, double luongMotNgay, int soLuongNhanVienDuoiQuyen) {
        super(hoVaTen, maSO, soDienThoai, soNgayLamViec, luongMotNgay);
        this.soLuongNhanVienDuoiQuyen = soLuongNhanVienDuoiQuyen;
    }

    public TruongPhong(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec, int soLuongNhanVienDuoiQuyen) {
        super(hoVaTen, maSO, soDienThoai, soNgayLamViec);
        this.soLuongNhanVienDuoiQuyen = soLuongNhanVienDuoiQuyen;
        this.setLuongMotNgay(200);
    }

    public TruongPhong(int soLuongNhanVienDuoiQuyen) {
        this.soLuongNhanVienDuoiQuyen = soLuongNhanVienDuoiQuyen;
    }

    public int getSoLuongNhanVienDuoiQuyen() {
        return soLuongNhanVienDuoiQuyen;
    }

    public void setSoLuongNhanVienDuoiQuyen(int soLuongNhanVienDuoiQuyen) {
        this.soLuongNhanVienDuoiQuyen = soLuongNhanVienDuoiQuyen;
    }

    @Override
    public double tinhLuong() {
        return this.getLuongMotNgay() * this.getSoNgayLamViec() + 100 * this.getSoLuongNhanVienDuoiQuyen();
    }
}
