package com.jabberpoint.prototype.Registries;

import com.jabberpoint.prototype.PrototypeRegistry;
import com.jabberpoint.style.Theme;

public class ThemeRegistry extends PrototypeRegistry<Theme> {

    public void addDefaultThemes() {
        Theme darkTheme = new Theme("Dark");

        Theme lightTheme = new Theme("Light");

        addPrototype("dark", darkTheme);
        addPrototype("light", lightTheme);
    }
}