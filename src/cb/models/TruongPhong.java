package cb.models;

import java.util.ArrayList;
import java.util.List;

public class TruongPhong extends NhanSu{
    private int soLuongNhanVienDuoiQuyen;
    private List<NhanVienThuong> danhSachNhanVienDuoiQuyen;

    public void display()
    {
        System.out.println("Trưởng phòng: ");
        super.display();
        System.out.println("Số lượng nhận viên dưới quyền: " + soLuongNhanVienDuoiQuyen);
    }
//    public TruongPhong(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
//        super(hoVaTen, maSO, soDienThoai, soNgayLamViec, luongMotNgay);
//        this.soLuongNhanVienDuoiQuyen = 0;
//    }

    public TruongPhong(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec) {
        super(hoVaTen, maSO, soDienThoai, soNgayLamViec);
        this.soLuongNhanVienDuoiQuyen = 0;
        this.setLuongMotNgay(200);
        this.danhSachNhanVienDuoiQuyen = new ArrayList<>();
    }

    public void inDanhSachNhanVienDuoiQuyen() {
        for (NhanVienThuong nvt : danhSachNhanVienDuoiQuyen)
        {
            nvt.display();
        }
    }

    public void setDanhSachNhanVienDuoiQuyen(NhanVienThuong nvt) {
        danhSachNhanVienDuoiQuyen.add(nvt);
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

    public void cacNhanVienDuoiQuyen()
    {
        System.out.println("\n" + this.getHoVaTen() + " quản lí các nhân viên:");
        for (int i = 0; i < danhSachNhanVienDuoiQuyen.size(); i++)
        {
            NhanVienThuong nvt = danhSachNhanVienDuoiQuyen.get(i);
            System.out.println("\n" + (i + 1) + ". ");
            nvt.display();
        }
    }

    public int kichThuocCuaDanhSachNhanVien()
    {
        return danhSachNhanVienDuoiQuyen.size();
    }


    public List<NhanVienThuong> getDanhSachNhanVienDuoiQuyen() {
        return danhSachNhanVienDuoiQuyen;
    }
}
