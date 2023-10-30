package cb.models;

public interface I_Menu {
    void addItem(String str);
    int getChoice();
    void showMenu();
    boolean confirmYesNo(String welcome);

}
