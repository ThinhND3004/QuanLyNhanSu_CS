package cb.controllers;

import cb.models.*;
import cb.utils.Utils;
import jdk.jshell.spi.ExecutionControlProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DanhSachNhanSu extends ArrayList<NhanSu> implements I_NhanSu {
    @Override
    public boolean add(CongTy congTy) {
        I_Menu menuAdd = new Menu();
        menuAdd.addItem("\n Bạn muốn thêm nhân sự vào vị trí nào:");
        menuAdd.addItem("1. Giám đốc");
        menuAdd.addItem("2. Trưởng phòng");
        menuAdd.addItem("3. Nhân viên thường");
        int choice;
        boolean check = false;
        boolean cont = true;
        try {
            do {
                menuAdd.showMenu();
                choice = menuAdd.getChoice();
                switch (choice)
                {
                    case 3:
                        boolean reInput = true;
                        String maSo;
                        do {
                            maSo = Utils.getString("\nNhập mã số của nhân viên: ");
                            if(checkDup(maSo))
                            {
                                System.out.println("\nMã số bạn vừa nhập đã bị trùng, mời nhập lại!");
                            } else reInput = false;
                        } while (reInput);

                        String ten = Utils.getString("Nhập tên nhân viên: ");
                        String sdt = Utils.getPhoneNumber("Nhập số điện thoại của nhan vin: ");
                        int soNgayLamViec = Utils.getNumber("Nhập số ngày làm việc của nhân viên: ", 0, 10000000);
                        NhanVienThuong newNhanVienThuong = new NhanVienThuong(ten, maSo, sdt, soNgayLamViec);
                        this.add(newNhanVienThuong);
                        check = true;
                        break;
                    case 2:
                        boolean checkCon = true;
                        String id;
                        do {
                            maSo = Utils.getString("\nNhập mã số của trưởng phòng: ");
                            if(checkDup(maSo))
                            {
                                System.out.println("\nMã số bạn vừa nhập đã bị trùng, mời nhập lại!");
                            } else checkCon = false;
                        } while (checkCon);

                        String tenTruongPhong = Utils.getString("Nhập tên trưởng phòng: ");
                        String sdtTruongPhong = Utils.getPhoneNumber("Nhập số điện thoại của trưởng phòng: ");
                        int soNgayLamViecTruongPhong = Utils.getNumber("Nhập số ngày làm việc của trưởng phòng: ", 0, 10000000);
                        TruongPhong tp = new TruongPhong(tenTruongPhong, maSo, sdtTruongPhong, soNgayLamViecTruongPhong);
                        this.add(tp);
                        check = true;
                        break;
                    case 1:
                        boolean checkGiamDoc = true;
                        String maSoGiamDoc;

                        do {
                            maSo = Utils.getString("\nNhập mã số của giám đốc: ");
                            if(checkDup(maSo))
                            {
                                System.out.println("\nMã số bạn vừa nhập đã bị trùng, mời nhập lại!");
                            } else checkGiamDoc = false;
                        } while (checkGiamDoc);
                        String tenGiamDoc = Utils.getString("Nhập tên giám đốc: ");
                        String soGiamDoc = Utils.getPhoneNumber("Nhập số điện thoại của giám đốc: ");
                        int soNgayLamViecGiamDoc = Utils.getNumber("Nhập số ngày làm việc của giám đốc: ", 0, 123123786);
                        double coPhan = congTy.getCoPhanCongTy();

                        double coPhanCuaGiamDoc = Utils.getNumber("Nhận cổ phân công ty của giám đốc: ", 0, coPhan);
                        congTy.setCoPhanCongTy(coPhan - coPhanCuaGiamDoc);
                        GiamDoc giamDoc = new GiamDoc(tenGiamDoc, maSo, soGiamDoc, soNgayLamViecGiamDoc, coPhanCuaGiamDoc);
                        this.add(giamDoc);
                        check = true;
                        break;
                }
                cont = Utils.getBoolean("\n Bạn có muốn tiếp tục thm nhân sự không? (Y/N)");
            } while (cont);
        } catch (Exception e)
        {
            System.out.println("\n Có gì đó sai sai!");
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(CongTy congTy)
    {
        boolean result = false;
        boolean cont = true;
        I_Menu deleteMenu = new Menu();
        deleteMenu.addItem("\nBạn muốn xóa loại nhân sự nào?");
        deleteMenu.addItem("1. Giám đốc");
        deleteMenu.addItem("2. Trưởng phòng");
        deleteMenu.addItem("3. Nhân viên");
        int choice = 0;
        try {
            do {
                deleteMenu.showMenu();
                choice = deleteMenu.getChoice();
                switch (choice)
                {
                    case 3:
                        boolean confirm;
                        NhanVienThuong nhanVien = getNhanVien();
                        if (nhanVien != null)
                        {
                            confirm = Utils.getBoolean("\nBạn có chắc muốn xóa nhân viên " + nhanVien.getHoVaTen() + " này không? (Y/N)");
                            if(confirm) {
                                for (NhanSu ns : this) {
                                    if (ns instanceof NhanVienThuong && ns.getMaSO().equals(nhanVien.getMaSO())) {
                                        for (NhanSu tp : this) {
                                            if (tp instanceof TruongPhong && ((NhanVienThuong) ns).getTruongPhongQuanLy().equals(tp.getHoVaTen())) {
                                                int soLuongNhanVienDuoiQuyenMoi = ((TruongPhong) tp).getSoLuongNhanVienDuoiQuyen() - 1;
                                                ((TruongPhong) tp).setSoLuongNhanVienDuoiQuyen(soLuongNhanVienDuoiQuyenMoi);
                                                this.remove(nhanVien);
                                                result = true;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        } else throw new Exception();
                        break;
                    case 2:
                        boolean confirmTruongPhong;
                        TruongPhong truongPhongMuonXoa = getTruongPhong();
                        if(truongPhongMuonXoa != null)
                        {
                            truongPhongMuonXoa.cacNhanVienDuoiQuyen();
                            confirmTruongPhong = Utils.getBoolean("\nBạn có chắc là muốn xóa trưởng phòng này không? (Y/N)");
                            if (confirmTruongPhong)
                            {
                                for (NhanSu nhanSu : this)
                                {
                                    if(nhanSu instanceof NhanVienThuong && ((NhanVienThuong) nhanSu).getTruongPhongQuanLy().equals(truongPhongMuonXoa.getMaSO()))
                                    {
                                        ((NhanVienThuong) nhanSu).setTruongPhongQuanLy("Chưa có trưởng phòng quản lí");
                                    }
                                }
                                this.remove(truongPhongMuonXoa);
                                result = true;
                            }
                        } else System.out.println("\nKhông có trưởng phòng nào!");
                        break;
                    case 1:
                        boolean confirmGiamDoc;
                        GiamDoc giamDoc = getGiamDoc();
                        if (giamDoc != null)
                        {
                            confirmGiamDoc = Utils.getBoolean("\nBạn có chắc muốn xóa giám đốc " + giamDoc.getHoVaTen() + " này không? (Y/N)");
                            if(confirmGiamDoc) {
                                for (NhanSu ns : this) {
                                    if(ns instanceof GiamDoc && ns.getMaSO().equals(giamDoc.getMaSO()))
                                    {
                                        System.out.println("\nGiám đốc " + giamDoc.getHoVaTen() + " có " + giamDoc.getCoPhanCongTy() + "% cổ phần trong công ty!");
                                        int option = Utils.getNumber("\nBạn muốn chuyển số cổ phần này cho giám đốc khác hay muốn trả về lại cho công ty? \n\t(1. Trả về | 2. Chuyển cho người khác)", 1, 2);
                                        if (option == 1)
                                        {
                                            double coPhanCuaCongTy = congTy.getCoPhanCongTy() + giamDoc.getCoPhanCongTy();
                                            congTy.setCoPhanCongTy(coPhanCuaCongTy);
                                        } else
                                        {
                                            System.out.println("\nBạn muốn chuyển số cổ phần này cho giám đốc nào?");
                                            GiamDoc giamDocDuocChon = getGiamDoc(giamDoc);
                                            if(giamDocDuocChon != null)
                                            {
                                                double coPhanChoGiamDocKhac = giamDoc.getCoPhanCongTy() + giamDocDuocChon.getCoPhanCongTy();
                                                giamDocDuocChon.setCoPhanCongTy(coPhanChoGiamDocKhac);

                                            } else
                                            {
                                                System.out.println("\nKhông có giám đốc nào khác trong công ty! Cổ phần của bạn sẽ được trả về lại công ty!");
                                            }
                                        }
                                        this.remove(giamDoc);
                                        result = true;
                                        break;
                                    }
                                }
                            }
                        } else throw new Exception();
                        break;
                }
                cont = Utils.getBoolean("\nBạn có tiếp tục muốn xóa nhân sự nữa không? (Y/N)");
            } while (cont);
        } catch (Exception e)
        {
            System.out.println("\nCó gì đó sai sai!");
        }

        return result;
    }
    @Override
    public NhanVienThuong nhanVienChuaCoTruongPhong()
    {
        NhanVienThuong nvChuaCoQuanLy = null;
        try {
            int j = 0;
            System.out.println("\nDanh sách nhân viên có trong công ty chưa có quản lý: ");
            NhanVienThuong[] nv = new NhanVienThuong[100];
            for (int i = 0; i < this.size(); i++)
            {
                NhanSu ns = this.get(i);
                if(ns instanceof NhanVienThuong && ((NhanVienThuong)ns).getTruongPhongQuanLy().equals("Chưa có trưởng phòng quản lí"))
                {
                    nv[j++] = (NhanVienThuong) ns;
                }
            }
            if(nv.length == 0)
            {
                return null;
            }
            for (int i = 0; i < nv.length; i++)
            {
                if(nv[i] == null) break;
                System.out.println("\n" + (i + 1) + ". ");
                nv[i].display();
            }
            int choice = Utils.getNumber("\nNhân viên bạn chọn là: ", 0, nv.length);
            for (int i = 0; i < nv.length; i++)
            {
                if(i == choice - 1)
                {
                    nvChuaCoQuanLy = nv[i];
                    break;
                }
            }
        } catch (Exception e) {}
        return nvChuaCoQuanLy;
    }

    @Override
    public void danhSachNhanVienDuoiQuyen(TruongPhong truongPhong) {
        if(truongPhong.kichThuocCuaDanhSachNhanVien() != 0)
        {
            for (int i = 0; i < truongPhong.kichThuocCuaDanhSachNhanVien(); i++)
            {
                System.out.println("\n" + (i + 1) + ". ");
                List<NhanVienThuong> danhSach = truongPhong.getDanhSachNhanVienDuoiQuyen();
                NhanVienThuong nvt = danhSach.get(i);
                nvt.display();
            }
        } else
        {
            System.out.println("\nQuản lí này không có bất kì nhân viên dưới quyền nào!");
        }

    }

    public void truongPhongQuanLi(NhanVienThuong nhanVienThuong)
    {
        for (NhanSu nhanSu : this)
        {
            if(nhanSu instanceof TruongPhong && nhanVienThuong.getTruongPhongQuanLy().equals(nhanSu.getMaSO()))
            {
                System.out.println("\n Quản lí của " + nhanVienThuong.getHoVaTen() + " là:");
                nhanSu.display();
            }
        }
    }

    @Override
    public NhanVienThuong getNhanVien() {
        NhanVienThuong nhanVienDuocLay = null;
        try {
            nhanVienDuocLay = null;
            int j = 0;
            System.out.println("\nDanh sách nhân viên có trong công ty: ");
            NhanVienThuong[] nv = new NhanVienThuong[100];
            for (int i = 0; i < this.size(); i++)
            {
                NhanSu ns = this.get(i);
                if(ns instanceof NhanVienThuong)
                {
                    nv[j++] = (NhanVienThuong) ns;
                }
            }
            if(nv.length == 0)
            {
                return null;
            }
            for (int i = 0; i < nv.length; i++)
            {
                if(nv[i] == null) break;
                System.out.println("\n" + (i + 1) + ". ");
                nv[i].display();
            }
            int choice = Utils.getNumber("\nNhân viên bạn chọn là: ", 0, nv.length);
            for (int i = 0; i < nv.length; i++)
            {
                if(i == choice - 1)
                {
                    nhanVienDuocLay = nv[i];
                    break;
                }
            }
        } catch (Exception e)
        {
            System.out.println("\nCó g đó sai sai!");
        }

        return nhanVienDuocLay;
    }

    @Override
    public TruongPhong getTruongPhong()
    {
        TruongPhong truongPhong = null;
        try {
            int j = 0;
            System.out.println("\nDanh sách trưởng phòng có trong công ty: ");
            TruongPhong[] truongPhongs = new TruongPhong[100];
            for (int i = 0; i < this.size(); i++)
            {
                NhanSu ns = this.get(i);
                if(ns instanceof TruongPhong)
                {
                    truongPhongs[j++] = (TruongPhong) ns;
                }
            }
            if(truongPhongs.length  == 0)
            {
                return truongPhong;
            }
            for (int i = 0; i < truongPhongs.length ; i++)
            {
                if (truongPhongs[i] == null) break;
                System.out.println("\n" + (i + 1) + ". ");
                truongPhongs[i].display();
            }
            int choice = Utils.getNumber("\nTrưởng phòng bạn chọn là: ", 0, truongPhongs.length - 1);
            for (int i = 0; i < truongPhongs.length ; i++)
            {
                if(i == choice - 1)
                {
                    truongPhong = truongPhongs[i];
                    break;
                }
            }
        } catch (Exception e)
        {
            System.out.println("\nCó gì đó sai sai!");
        }

        return truongPhong;
    }

    @Override
    public GiamDoc getGiamDoc() {
        GiamDoc giamDoc = null;
        try {
            int j = 0;
            System.out.println("\nDanh sách giám đốc có trong công ty: ");
            GiamDoc[] giamDocs = new GiamDoc[100];
            for (int i = 0; i < this.size(); i++)
            {
                NhanSu ns = this.get(i);
                if(ns instanceof GiamDoc)
                {
                    giamDocs[j++] = (GiamDoc) ns;
                }
            }
            if(giamDocs.length  == 0)
            {
                return giamDoc;
            }
            for (int i = 0; i < giamDocs.length ; i++)
            {
                if (giamDocs[i] == null) break;
                System.out.println("\n" + (i + 1) + ". ");
                giamDocs[i].display();
            }
            int choice = Utils.getNumber("\nGiám đốc bạn chọn là: ", 0, giamDocs.length);
            for (int i = 0; i < giamDocs.length ; i++)
            {
                if(i == choice - 1)
                {
                    giamDoc = giamDocs[i];
                    break;
                }
            }
        } catch (Exception e)
        {
            System.out.println("\nCó gì đó sai sai!");
        }
        return giamDoc;
    }

    @Override
    public GiamDoc getGiamDoc(GiamDoc giamDoc1)
    {
        GiamDoc giamDoc = null;
        try {
            int j = 0;
            System.out.println("\nDanh sách giám đốc có trong công ty: ");
            GiamDoc[] giamDocs = new GiamDoc[100];
            for (int i = 0; i < this.size(); i++)
            {
                NhanSu ns = this.get(i);
                if(ns instanceof GiamDoc && !ns.getMaSO().equals(giamDoc1.getMaSO()))
                {
                    giamDocs[j++] = (GiamDoc) ns;
                }
            }
            if(giamDocs.length  == 0)
            {
                return giamDoc;
            }
            for (int i = 0; i < giamDocs.length ; i++)
            {
                if (giamDocs[i] == null) break;
                System.out.println("\n" + (i + 1) + ". ");
                giamDocs[i].display();
            }
            int choice = Utils.getNumber("\nGiám đốc bạn chọn là: ", 0, giamDocs.length);
            for (int i = 0; i < giamDocs.length ; i++)
            {
                if(i == choice - 1)
                {
                    giamDoc = giamDocs[i];
                    break;
                }
            }
        } catch (Exception e)
        {
            System.out.println("\nCó gì đó sai sai!");
        }
        return giamDoc;
    }

    @Override
    public boolean phanBoNhanVien() {
        boolean result = false;
        boolean cont = true;
        try {
            do {
                NhanVienThuong nhanVienThuong = nhanVienChuaCoTruongPhong();
                TruongPhong truongPhong = getTruongPhong();
                if(nhanVienThuong == null || truongPhong == null)
                {
                    System.out.println("Danh sách lỗi");
                }
                String maQuanLy = truongPhong.getMaSO();
                nhanVienThuong.setTruongPhongQuanLy(maQuanLy);
                int soNhanVienDuoiQuyen = truongPhong.getSoLuongNhanVienDuoiQuyen();
                truongPhong.setDanhSachNhanVienDuoiQuyen(nhanVienThuong);
                truongPhong.setSoLuongNhanVienDuoiQuyen(++soNhanVienDuoiQuyen);
                result = true;

                cont = Utils.getBoolean("\nBạn muốn phân bổ nhân sự cho quản lí nào nữa không? (Y/N) ");
            } while (cont);
            showAll();
        } catch (Exception e)
        {
            System.out.println("\nHmmmm....Có lẽ là...danh sách nhân viên hoặc trưởng phòng đang trống!");
        }
        return result;
    }

    @Override
    public boolean checkDup(String something) {
        boolean check = false;
        for (NhanSu ns : this)
        {
            if(ns.getMaSO().equals(something))
            {
                check = true;
                break;
            } else
            {
                break;
            }
        }
        return check;
    }

    @Override
    public void showAll() {
        for (int i = 0; i < this.size(); i++)
        {
            NhanSu ns = this.get(i);
            System.out.println("\n" + (i + 1) + ". ");
            ns.display();
        }
    }

    @Override
    public NhanVienThuong nhanVienCoLuongCaoNhat() {
        NhanVienThuong nhanVienResult = null;
        int indexNhanVien = 0;
        NhanVienThuong[] danhSachNhanVien = new NhanVienThuong[100];
        NhanSu ns = null;
        for(int i = 0; i < this.size(); i++)
        {
            ns = this.get(i);
            if(ns instanceof NhanVienThuong)
            {
                danhSachNhanVien[indexNhanVien++] = (NhanVienThuong) ns;
            }
        }
        int max = danhSachNhanVien[0].getSoNgayLamViec();
        nhanVienResult = danhSachNhanVien[0];
        for (NhanVienThuong nvt : danhSachNhanVien)
        {
            if(nvt != null && nvt.getSoNgayLamViec() > max)
            {
                max = nvt.getSoNgayLamViec();
                nhanVienResult = nvt;
            }
        }
        return nhanVienResult;
    }


    @Override
    public void xuatRaToanBoNguoiTrongCongTy() {
        try {
            GiamDoc[] giamDocs = new GiamDoc[100];
            int indexGiamDoc = 0;
            int indexTruongPhong = 0;
            int indexNhanVien = 0;
            TruongPhong[] truongPhongs = new TruongPhong[100];
            NhanVienThuong[] nhanVienThuongs = new NhanVienThuong[100];
            NhanSu nhanSu = null;

            for (int i = 0; i < this.size(); i++)
            {
                nhanSu = this.get(i);
                if(nhanSu instanceof GiamDoc)
                {
                    giamDocs[indexGiamDoc++] = (GiamDoc) nhanSu;
                } else if(nhanSu instanceof TruongPhong)
                {
                    truongPhongs[indexTruongPhong++] = (TruongPhong) nhanSu;
                } else
                {
                    nhanVienThuongs[indexNhanVien++] = (NhanVienThuong) nhanSu;
                }
            }
            if(giamDocs.length != 0)
            {
                System.out.println("\nGIÁM ĐỐC");
                for (int i = 0; i < giamDocs.length; i++)
                {
                    if(giamDocs[i] == null) break;
                    System.out.println("\n" + (i + 1) + ". ");
                    giamDocs[i].display();
                }
            } else System.out.println("\nKhông có GIÁM ĐỐC trong công ty của bạn!");
            if(truongPhongs.length != 0)
            {
                System.out.println("\nTRƯỞNG PHÒNG");
                for (int i = 0; i < truongPhongs.length; i++)
                {
                    if(truongPhongs[i] == null) break;
                    System.out.println("\n" + (i + 1) + ". ");
                    truongPhongs[i].display();
                }
            } else System.out.println("\nKhông có TRƯỞNG PHÒNG trong công ty của bạn!");
            if(nhanVienThuongs.length != 0)
            {
                System.out.println("\nNHÂN VIÊN THƯỜNG:");
                for (int i = 0; i < nhanVienThuongs.length; i++)
                {
                    if(nhanVienThuongs[i] == null) break;
                    System.out.println("\n" + (i + 1) + ". ");
                    nhanVienThuongs[i].display();
                }
            } else System.out.println("\n Không có NHÂN VIÊN THƯỜNG trong công ty của bạn!");

        } catch (Exception e)
        {
            System.out.println("\nCó gì đó sai sai!");
        }
    }

    @Override
    public TruongPhong truongPhongCoSoLuongNhanVienDuoiQuyenNhieuNhat() {
        TruongPhong truongPhongResult = null;
        NhanSu ns = null;
        TruongPhong[] truongPhongs = new TruongPhong[100];
        int indexTruongPhong = 0;
        try {
            for (int i = 0; i < this.size(); i++)
            {
                ns = this.get(i);
                if (ns instanceof TruongPhong)
                {
                    truongPhongs[indexTruongPhong++] = (TruongPhong) ns;
                }
            }
            if (truongPhongs != null)
            {
                int max = truongPhongs[0].getSoLuongNhanVienDuoiQuyen();
                truongPhongResult = truongPhongs[0];
                for (TruongPhong truongPhong : truongPhongs)
                {
                    if(truongPhong != null && truongPhong.getSoLuongNhanVienDuoiQuyen() > max)
                    {
                        max = truongPhong.getSoLuongNhanVienDuoiQuyen();
                        truongPhongResult = truongPhong;
                    }
                }
            }
        } catch (Exception e) {}

        return truongPhongResult;
    }

    @Override
    public void sapXepTenNhanVienTheoThuTuABC() {
        Collections.sort(this, new Comparator<NhanSu>() {
            @Override
            public int compare(NhanSu o1, NhanSu o2) {
                String name1 = o1.getHoVaTen();
                String name2 = o2.getHoVaTen();
                return name1.compareToIgnoreCase(name2);
            }
        });
    }

    @Override
    public void sapXepTheoThuTuLuongTrongCongTy() {
        Collections.sort(this, new Comparator<NhanSu>() {
            @Override
            public int compare(NhanSu o1, NhanSu o2) {
                double luong1 = o1.getLuongMotNgay() * o1.getSoNgayLamViec();
                double luong2 = o2.getLuongMotNgay() * o2.getSoNgayLamViec();

                if (luong1 > luong2) {
                    return -1;
                } else if (luong1 < luong2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    @Override
    public GiamDoc giamDocCoCoPhanNhieuNhat() {
        GiamDoc giamDocResult = null;

        try {
            List<GiamDoc> giamDocs = new ArrayList<>();

            for (NhanSu ns : this) {
                if (ns instanceof GiamDoc) {
                    giamDocs.add((GiamDoc) ns);
                }
            }

            if (!giamDocs.isEmpty()) {
                double max = giamDocs.get(0).getCoPhanCongTy();
                giamDocResult = giamDocs.get(0);
                for (GiamDoc giamDoc : giamDocs) {
                    if (giamDoc.getCoPhanCongTy() > max) {
                        max = giamDoc.getCoPhanCongTy();
                        giamDocResult = giamDoc;
                    }
                }
            } else {
                System.out.println("Không có giám đốc nào trong công ty của bạn!");
            }
        } catch (Exception e) {
            System.out.println("Quá trình lấy giám đốc xảy ra lỗi!");
        }

        return giamDocResult;
    }

    @Override
    public void tinhVaXuatTongThuNhapCuaGiamDoc(CongTy congTy) {
        int i = 0;
        List<GiamDoc> giamDocs = new ArrayList<>();
        try {
            for (NhanSu nhanSu : this)
            {
                if( nhanSu instanceof GiamDoc)
                {
                    giamDocs.add((GiamDoc) nhanSu);
                }
            }
            if(!giamDocs.isEmpty())
            {
                for (GiamDoc giamDoc : giamDocs)
                {
                    System.out.println("\n" + ++i + ". ");
                    giamDoc.display();
                    System.out.println("THU NHẬP: " + String.format("%.2f", giamDoc.getSoNgayLamViec() * giamDoc.getLuongMotNgay()) +
                            (giamDoc.getCoPhanCongTy() * (congTy.getDoanhThuHangThang() - tongLuongToanCongTy())));
                }
            }
        } catch (Exception e)
        {
            System.out.println("\nQuá trình tính tổng thu nhập của từng giám đốc xảy ra lỗi!");
        }

    }

    @Override
    public double tongLuongToanCongTy() {
        double result = 0;
        for (NhanSu ns : this)
        {
            if (ns instanceof GiamDoc)
            {

                result += ((GiamDoc) ns).getLuongMotNgay() * ((GiamDoc) ns).getSoNgayLamViec();
            } else if (ns instanceof TruongPhong)
            {

                result += ((TruongPhong) ns).getLuongMotNgay() * ((TruongPhong) ns).getSoNgayLamViec();
            } else
            {

                result += ((NhanVienThuong) ns).getLuongMotNgay() * ((NhanVienThuong) ns).getSoNgayLamViec();
            }
        }
        return result;
    }


}
