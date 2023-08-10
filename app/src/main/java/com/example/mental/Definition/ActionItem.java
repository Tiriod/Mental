package com.example.mental.Definition;

public class ActionItem {
    private int iconResId;
    private String itemName;
    private boolean isSelected;

    public ActionItem(int iconResId, String itemName) {
        this.iconResId = iconResId;
        this.itemName = itemName;
        this.isSelected = false;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
