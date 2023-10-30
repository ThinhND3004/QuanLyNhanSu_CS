package cb.models;

public class CongTy {
    private String tenCongTy;
    private String maSoThue;
    private long doanhThuHangThang;

    public CongTy(String tenCongTy, String maSoThue, long doanhThuHangThang) {
        this.tenCongTy = tenCongTy;
        this.maSoThue = maSoThue;
        this.doanhThuHangThang = doanhThuHangThang;
    }

    public CongTy() {
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public long getDoanhThuHangThang() {
        return doanhThuHangThang;
    }

    public void setDoanhThuHangThang(long doanhThuHangThang) {
        this.doanhThuHangThang = doanhThuHangThang;
    }
}
