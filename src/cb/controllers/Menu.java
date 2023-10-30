package cb.controllers;

import cb.models.I_Menu;
import cb.utils.Utils;

import java.util.ArrayList;

public class Menu extends ArrayList<String> implements I_Menu {
    public Menu() {
        super();
    }

    @Override
    public void addItem(String str) {
        this.add(str);
    }

    @Override
    public int getChoice() {
        return Utils.getNumber("Input your choice: ", 0, this.size());
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++)
        {
            System.out.println(this.get(i));
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        result = Utils.getBoolean(welcome);
        return result;
    }
}
