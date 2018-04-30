package pranavmahajan21.com.viewpagermaterial.model;

/* Created by Pranav on 5/9/17 */

public class LogoColor {

    String iconColor;
    String bgColor;


    public LogoColor(String iconColor, String bgColor) {
        this.iconColor = iconColor;
        this.bgColor = bgColor;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
