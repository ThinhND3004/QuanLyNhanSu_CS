package cb.controllers;

import cb.models.I_NhanSu;
import cb.models.I_NhanVienThuong;
import cb.models.NhanVienThuong;
import cb.utils.Utils;

import java.util.ArrayList;

public class DanhSachNhanVienThuong extends ArrayList<NhanVienThuong> implements I_NhanVienThuong {

    @Override
    public boolean add() {
        boolean check = false;
        boolean cont = true;
        do {
            try {
                boolean checkDup = true;
                String ten;
                do {
                    ten = Utils.getString("\nNhập tên của nhân viên: ");
                    for (NhanVienThuong nhanVienThuong : this)
                    {
                        if(nhanVienThuong.getHoVaTen().equals(ten))
                        {
                            System.out.println("Mã số nhân viên này đã tồn tại!");
                        } else checkDup = false;
                    }
                } while (checkDup);

                String maSo = Utils.getString("Nhập mã số nhân viên: ");
                String sdt = Utils.getString("Nhập số điện thoại của nhan vin: ");
                int soNgayLamViec = Utils.getNumber("Nhập số ngày làm việc của nhân viên: ", 0, 10000000);
                NhanVienThuong newNhanVienThuong = new NhanVienThuong(ten, maSo, sdt, soNgayLamViec);
                this.add(newNhanVienThuong);
                check = true;
            } catch (Exception e)
            {
                System.out.println("\nCó gì đó sai sai!");
                break;
            }

            cont = Utils.getBoolean("\nBạn có muốn tiếp tục thêm nhân viên không?");
        } while (cont);
        return check;
    }

    @Override
    public boolean delete(int index) {
        boolean check = false;
        boolean cont;

        return check;
    }

}
