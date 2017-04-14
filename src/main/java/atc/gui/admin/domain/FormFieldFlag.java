package atc.gui.admin.domain;

public class FormFieldFlag
{
	public static final long NO_FLAGS = 0;
	
	public static final long DISABLED = 1;
	public static final long HIDDEN = 2;
	
	public static final long ADD_ENABLED = 8;
	public static final long ADD_VISIBLE = 16;
	
	public static final long EDIT_ENABLED = 128;
	public static final long EDIT_VISIBLE = 256;
	
	public static final long LIST_VISIBLE = 1024;
	public static final long LIST_SORT_ENABLED = 2048;
	
}
