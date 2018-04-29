package pranavmahajan21.com.viewpagermaterial.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/* Created by Pranav on 5/9/17 */

@IgnoreExtraProperties
public class MenuItem {

    String realName;
    String aliasName;
    int position;


    boolean isCustomMenuItem = false;
    String menuItemContent;

    public MenuItem() {
    }

    public MenuItem(String realName, String aliasName, int position) {
        this.realName = realName;
        this.aliasName = aliasName;
        this.position = position;
    }

    public MenuItem(boolean isCustomMenuItem, String realName, String aliasName, String menuItemContent, int position) {
        this.isCustomMenuItem = isCustomMenuItem;
        this.realName = realName;
        this.aliasName = aliasName;
        this.menuItemContent = menuItemContent;
        this.position = position;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isCustomMenuItem() {
        return isCustomMenuItem;
    }

    public void setCustomMenuItem(boolean customMenuItem) {
        isCustomMenuItem = customMenuItem;
    }

    public String getMenuItemContent() {
        return menuItemContent;
    }

    public void setMenuItemContent(String menuItemContent) {
        this.menuItemContent = menuItemContent;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("realName", realName);
        result.put("aliasName", aliasName);
        result.put("position", position);
        result.put("menuItemContent", menuItemContent);
        result.put("isCustomMenuItem", isCustomMenuItem);

        return result;
    }
}
