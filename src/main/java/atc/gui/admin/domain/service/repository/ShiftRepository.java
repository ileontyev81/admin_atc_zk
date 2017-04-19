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

package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ShiftEntry;
import atc.gui.admin.domain.model.ShiftWorkdayEntry;
import atc.gui.admin.domain.model.ShiftWorkdayExcludeEntry;
import atc.gui.admin.domain.model.ShiftWorkdayTimeEntry;

public interface ShiftRepository
{
	public void saveShift(ShiftEntry e);
	public ShiftEntry getShift(int id);
	public List<ShiftEntry> getShiftList();
	public void deleteShift(int id);
	
	//WORKDAY
	public void saveWorkday(ShiftWorkdayEntry e);
	public ShiftWorkdayEntry getWorkday(int id, int shiftId);
	public List<ShiftWorkdayEntry> getWorkdayList(int shiftId);
	public void deleteWorkday(int id);
	
	//TIME
	public void saveWorkdayTime(ShiftWorkdayTimeEntry e);
	public ShiftWorkdayTimeEntry getWorkdayTime(int id, int workdayId);
	public List<ShiftWorkdayTimeEntry> getWorkdayTimeList(int workdayId);
	public void deleteWorkdayTime(int id);
	
	//EXCLUDE
	public void saveWorkdayExclude(ShiftWorkdayExcludeEntry e);
	public ShiftWorkdayExcludeEntry getWorkdayExclude(int id, int workdayId);
	public List<ShiftWorkdayExcludeEntry> getWorkdayExcludeList(int workdayId);
	public void deleteWorkdayExclude(int id);
}
