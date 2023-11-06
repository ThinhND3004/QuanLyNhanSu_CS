package cb.view;

import cb.controllers.DanhSachNhanSu;
import cb.controllers.Menu;
import cb.models.*;
import cb.utils.Utils;

public class QuanLyNhanSu {

    public static void main(String[] args) {
        try {
            I_Menu menu = new Menu();
            I_Menu deleteAddMenu = new Menu();
            menu.addItem("\n1. Nhập thông tin công ty");
            menu.addItem("2. Phân bổ nhân viên vào trưởng phòng");
            menu.addItem("3. THêm hoặc xóa thông tin nhân sự");
            menu.addItem("4. Xuất ra thông tin toàn bộ người trong công ty");
            menu.addItem("5. Tính và xuất tổng lương cho toàn công ty");
            menu.addItem("6. Tìm nhân viên có lương cao nhất");
            menu.addItem("7. Tìm trưởng phòng có số lượng nhân viên dưới quyền cao nhất");
            menu.addItem("8. Sắp xếp nhân viên toan công ty theo th tự ABC");
            menu.addItem("9. Sắp xếp nhân viên toan công ty theo th tự lương giảm dần");
            menu.addItem("10. Giám đốc có số lượng cổ phần nhiều nhất");
            menu.addItem("11. Tính và xuất tổng thu nhập của từng giám đốc");
            menu.addItem("12. Xuất thông tin quản lí của nhân viên");
            menu.addItem("13. Xuất tất cả nhân viên dưới quyền của trưởng phòng");
            menu.addItem("0. Kết thúc chương trình.\n");
            deleteAddMenu.addItem("\nBạn muốn xóa hay thêm nhân sự ?");
            deleteAddMenu.addItem("1. Thêm");
            deleteAddMenu.addItem("2. Xóa");
            int subChoice;
            int choice;
            boolean cont = true;
            CongTy congTy = null;
            I_NhanSu danhSachNhanSuCuaCongTy = new DanhSachNhanSu();
            do {
                menu.showMenu();
                choice = menu.getChoice();
                if (choice == 1) {
                    try {
                        String tenCongTy = Utils.getString("Nhập tên của công ty: ");
                        String maSoThue = Utils.getString("Nhập mã số thuế của công ty: ");
                        long doanhThu = Utils.getNumber("Nhập doanh thu của công ty: ", 0, 99999999);
                        congTy = new CongTy(tenCongTy, maSoThue, doanhThu);
                        System.out.println("\nNhập thông tin công ty thành công!");
                    } catch (Exception e) {
                        System.out.println("\nCó gì đó sai sai!");
                    }
                } else if (congTy != null) {
                    switch (choice) {
                        case 0:
                            boolean out = Utils.getBoolean("\nBạn có chắc là muốn thoát khong?");
                            if (out == true) {
                                cont = false;
                            }
                            break;
                        case 2:
                            danhSachNhanSuCuaCongTy.phanBoNhanVien();
                            break;
                        case 3:
                            boolean tiepTuc = true;
                            do {
                                deleteAddMenu.showMenu();
                                subChoice = deleteAddMenu.getChoice();
                                switch (subChoice)
                                {
                                    case 1:
                                        if (danhSachNhanSuCuaCongTy.add(congTy)) {
                                            System.out.println("\nThêm nhân sự thành công!");
                                            danhSachNhanSuCuaCongTy.showAll();
                                        } else System.out.println("\nXảy ra lỗi trong quá trình thêm nhân sự!");
                                        break;
                                    case 2:
                                        if (danhSachNhanSuCuaCongTy.delete(congTy)) {
                                            System.out.println("\nXóa nhân sự thành công!");
                                            danhSachNhanSuCuaCongTy.showAll();
                                        } else System.out.println("\nXảy ra lỗi trong quá trình xóa nhân sự!");
                                        break;
                                }
                                tiepTuc = Utils.getBoolean("\n Bạn có muốn tiếp tục thêm hoặc xóa nhân sự nữa không ? (Y/N)");
                            } while (tiepTuc);

                            break;
                        case 4:
                            danhSachNhanSuCuaCongTy.xuatRaToanBoNguoiTrongCongTy();
                            break;
                        case 5:
                            double tongLuongToanCongTY = danhSachNhanSuCuaCongTy.tongLuongToanCongTy();
                            if (tongLuongToanCongTY != 0)
                            {
                                System.out.println("\nTổng lương toàn công ty: " + tongLuongToanCongTY);
                            }
                            break;
                        case 6:
                            NhanVienThuong nhanVienCoLuongCaoNhat = danhSachNhanSuCuaCongTy.nhanVienCoLuongCaoNhat();
                            if(nhanVienCoLuongCaoNhat != null)
                            {
                                nhanVienCoLuongCaoNhat.display();
                                System.out.println("Có mức lương: " + (nhanVienCoLuongCaoNhat.getSoNgayLamViec() * nhanVienCoLuongCaoNhat.getLuongMotNgay()));
                            } else System.out.println("\nCó lỗi xảy ra trong quá trính lấy nhân viên!");

                            break;
                        case 7:
                            TruongPhong tpCoSoLuongNhanVienDuoiQuyenCaoNhat = danhSachNhanSuCuaCongTy.truongPhongCoSoLuongNhanVienDuoiQuyenNhieuNhat();
                            if(tpCoSoLuongNhanVienDuoiQuyenCaoNhat != null)
                            {
                                System.out.println("\nTrưởng phòng có số lượng nhân viên dưới quyền nhiều nhất là:\n");
                                tpCoSoLuongNhanVienDuoiQuyenCaoNhat.display();
                            } else System.out.println("\nDanh sách trưởng phòng rỗng!");
                            break;
                        case 8:
                            danhSachNhanSuCuaCongTy.sapXepTenNhanVienTheoThuTuABC();
                            danhSachNhanSuCuaCongTy.showAll();
                            break;
                        case 9:
                            danhSachNhanSuCuaCongTy.sapXepTheoThuTuLuongTrongCongTy();
                            danhSachNhanSuCuaCongTy.showAll();
                            break;
                        case 10:
                            GiamDoc giamDocCoCoPhanNhieuNhat = danhSachNhanSuCuaCongTy.giamDocCoCoPhanNhieuNhat();
                            if (giamDocCoCoPhanNhieuNhat != null)
                            {
                                System.out.println("\nGiám đốc có lượng cổ phần nhiều nhất là:");
                                giamDocCoCoPhanNhieuNhat.display();
                            } else System.out.println("\nKhông có giám đốc nào cả!");
                            break;
                        case 11:
                            danhSachNhanSuCuaCongTy.tinhVaXuatTongThuNhapCuaGiamDoc(congTy);
                            break;
                        case 12:
                            NhanVienThuong nhanVienDuocChon = danhSachNhanSuCuaCongTy.getNhanVien();
                            if (nhanVienDuocChon != null)
                            {
                                danhSachNhanSuCuaCongTy.truongPhongQuanLi(nhanVienDuocChon);
                            } else System.out.println("\nKhông có nhân viên thường nào trong công ty!");
                            break;
                        case 13:
                            TruongPhong truongPhong = danhSachNhanSuCuaCongTy.getTruongPhong();
                            if (truongPhong != null)
                            {
                                danhSachNhanSuCuaCongTy.danhSachNhanVienDuoiQuyen(truongPhong);
                            } else
                            {
                                System.out.println("\nKhông có trưởng phòng nào trong công ty");
                            }
                            break;
                    }
                } else System.out.println("\n Công ty của bạn là gì?? Bạn chưa nhập thông tin công ty của mình!");
                } while (cont) ;
            } catch (Exception e)
            {
                System.out.println("\nLỗi rồi!!!!");
            }
    }
}
