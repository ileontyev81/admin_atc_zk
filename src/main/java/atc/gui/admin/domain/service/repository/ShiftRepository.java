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
