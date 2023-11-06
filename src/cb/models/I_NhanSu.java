package cb.models;

public interface I_NhanSu {
    boolean add(CongTy congTy);
    boolean delete(CongTy congTy);

    NhanVienThuong getNhanVien();
    TruongPhong getTruongPhong();
    GiamDoc getGiamDoc();
    GiamDoc getGiamDoc(GiamDoc giamDoc);

    boolean phanBoNhanVien();

    boolean checkDup(String something);

    void showAll();

    NhanVienThuong nhanVienCoLuongCaoNhat();
    void xuatRaToanBoNguoiTrongCongTy();
    TruongPhong truongPhongCoSoLuongNhanVienDuoiQuyenNhieuNhat();

    void sapXepTenNhanVienTheoThuTuABC();

    void sapXepTheoThuTuLuongTrongCongTy();
    GiamDoc giamDocCoCoPhanNhieuNhat();

    void tinhVaXuatTongThuNhapCuaGiamDoc(CongTy congTy);
    double tongLuongToanCongTy();
    NhanVienThuong nhanVienChuaCoTruongPhong();

    void danhSachNhanVienDuoiQuyen(TruongPhong truongPhong);

    void truongPhongQuanLi(NhanVienThuong nhanVienThuong);
}
