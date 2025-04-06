package com.jabberpoint.prototype.Registries;

import com.jabberpoint.prototype.PrototypeRegistry;
import com.jabberpoint.style.Style;
import java.awt.Color;
import com.jabberpoint.style.FontName;

public class StyleRegistry extends PrototypeRegistry<Style> {

    public StyleRegistry() {
        super();
        initializeDefaultStyles();
    }

    private void initializeDefaultStyles() {
        Style titleStyle = new Style(0, Color.BLACK, 36, 20, FontName.ARIAL);
        addPrototype("title", titleStyle);

        Style headingStyle = new Style(20, new Color(0, 0, 128), 30, 15, FontName.TIMES_NEW_ROMAN);
        addPrototype("heading", headingStyle);

        Style bodyStyle = new Style(40, Color.BLACK, 24, 10, FontName.ARIAL);
        addPrototype("body", bodyStyle);

        Style listStyle = new Style(60, new Color(0, 0, 0), 22, 10, FontName.ARIAL);
        addPrototype("list", listStyle);
    }

    public Style getTitleStyle() {
        return getPrototype("title");
    }

    public Style getHeadingStyle() {
        return getPrototype("heading");
    }
}