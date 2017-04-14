package atc.gui.admin.zk;

import java.util.Arrays;

public enum UIType
{
    COMBOBOX, TEXTFIELD, PASSWORD, SELECT, LIST, CHECKBOX, DATE, TIME, DATE_TIME, DATE_RANGE, TIME_RANGE, TEXTAREA, CODEBOX, SELECT_NO_ITEM, AUDIO;
    
    public boolean isMultiRow()
    {
        return Arrays.asList(new UIType[] {COMBOBOX, SELECT, LIST, SELECT_NO_ITEM}).contains(this); 
    }

}
