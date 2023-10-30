package cb.models;

public abstract class NhanSu {
    private String hoVaTen;
    private String maSO;
    private String soDienThoai;
    private int soNgayLamViec;
    private double luongMotNgay;
    public abstract double tinhLuong();
    public void display()
    {
        System.out.println("Họ Và Tên= " + hoVaTen + '\'' +
                ", Mã số = " + maSO + '\'' +
                ", Số điện thoại = " + soDienThoai + '\'' +
                ", Số ngày làm việc = " + soNgayLamViec +
                ", Lương một ngày = " + luongMotNgay);

    }
    public NhanSu(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
        this.hoVaTen = hoVaTen;
        this.maSO = maSO;
        this.soDienThoai = soDienThoai;
        this.soNgayLamViec = soNgayLamViec;
        this.luongMotNgay = luongMotNgay;
    }

    public NhanSu(String hoVaTen, String maSO, String soDienThoai, int soNgayLamViec) {
        this.hoVaTen = hoVaTen;
        this.maSO = maSO;
        this.soDienThoai = soDienThoai;
        this.soNgayLamViec = soNgayLamViec;
    }


    public NhanSu() {
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getMaSO() {
        return maSO;
    }

    public void setMaSO(String maSO) {
        this.maSO = maSO;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getSoNgayLamViec() {
        return soNgayLamViec;
    }

    public void setSoNgayLamViec(int soNgayLamViec) {
        this.soNgayLamViec = soNgayLamViec;
    }

    public double getLuongMotNgay() {
        return luongMotNgay;
    }

    public void setLuongMotNgay(double luongMotNgay) {
        this.luongMotNgay = luongMotNgay;
    }
}
