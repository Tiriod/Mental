package com.example.mental.Definition;

public class FunctionModule {
    private final int iconResId;
    private final String moduleName;

    public FunctionModule(int iconResId, String moduleName) {
        this.iconResId = iconResId;
        this.moduleName = moduleName;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getModuleName() {
        return moduleName;
    }
}

