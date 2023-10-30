package cb.view;

import cb.controllers.Menu;
import cb.models.CongTy;
import cb.models.I_Menu;
import cb.utils.Utils;

public class QuanLyNhanSu {

    public static void main(String[] args) {

        I_Menu menu = new Menu();
        menu.addItem("1. Nhập thông tin công ty");
        menu.addItem("2. Phân bổ nhân viên vào trưởng phòng");
        menu.addItem("3. THêm hoặc xóa thông tin nhân sự");
        menu.addItem("4. Xuất ra thông tin toàn bộ người trong công ty");
        menu.addItem("5. Tính và xuất tổng lương cho toàn công ty");
        menu.addItem("6. Tìm nhân viên có lương cao nhất");
        menu.addItem("7. Tìm trưởng phòng có số lượng nhân viên dưới quyền cao nhất");
        menu.addItem("8. Sắp xếp nhân viên toan công ty theo th tự ABC");
        menu.addItem("9. Sắp xếp nhân viên toan công ty theo th tự lương giảm dần");
        menu.addItem("10. Giám đốc có số lượng cổ phần nhiều nhất");
        menu.addItem("11. Tính và xuất tổng thu nhập của từng giám đốc\n");
        menu.addItem("0. Kết thúc chương trình.");
        int choice;
        boolean cont = true;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice)
            {
                case 0:
                    boolean out = Utils.getBoolean("Bạn có chắc là muốn thoát khong?");
                    if (out == true)
                    {
                        cont = false;
                    }
                    break;
                case 1:
                    try {
                        String tenCongTy = Utils.getString("Nhập tên của công ty");
                        String maSoThue = Utils.getString("Nhập mã số thuế của công ty");
                        long doanhThu = Utils.getNumber("Nhập doanh thu của công ty:", 0, 99999999);
                        CongTy congTy = new CongTy(tenCongTy, maSoThue, doanhThu);
                        System.out.println("\nNhập thông tin công ty thành công!");
                    } catch (Exception e)
                    {
                        System.out.println("\nCó gì đó sai sai!");
                    }
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
            }
        } while (cont);
    }
}
