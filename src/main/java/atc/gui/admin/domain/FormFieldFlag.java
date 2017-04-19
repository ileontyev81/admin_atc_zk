/*
 * Copyright (C) 2017 i.leontyev81@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
