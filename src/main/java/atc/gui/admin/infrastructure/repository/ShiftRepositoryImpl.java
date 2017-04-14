package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.ShiftEntry;
import atc.gui.admin.domain.model.ShiftWorkdayEntry;
import atc.gui.admin.domain.model.ShiftWorkdayExcludeEntry;
import atc.gui.admin.domain.model.ShiftWorkdayTimeEntry;
import atc.gui.admin.domain.service.repository.ShiftRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("shiftRepository")
public class ShiftRepositoryImpl implements ShiftRepository
{
	private JdbcTemplate template;

	@Autowired
	public ShiftRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	@Override
	public void saveShift(ShiftEntry e)
	{
		DbRequest<ShiftEntry> req = new DbRequest<ShiftEntry>(template, "wadm_shift_save", ShiftEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ShiftEntry getShift(int id)
	{
		DbRequest<ShiftEntry> req = new DbRequest<ShiftEntry>(template, "wadm_shift_get", ShiftEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
		return req.singleResult();
	}

	@Override
	public List<ShiftEntry> getShiftList()
	{
		DbRequest<ShiftEntry> req = new DbRequest<ShiftEntry>(template, "wadm_shift_get_list", ShiftEntry.class);
		return req.execute();
	}

	@Override
	public void deleteShift(int id)
	{
		DbRequest<ShiftEntry> req = new DbRequest<ShiftEntry>(template, "wadm_shift_delete", ShiftEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	
	//WORKDAY
	@Override
	public void saveWorkday(ShiftWorkdayEntry e)
	{
		DbRequest<ShiftWorkdayEntry> req = new DbRequest<ShiftWorkdayEntry>(template, "wadm_shift_workday_save", ShiftWorkdayEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ShiftWorkdayEntry getWorkday(int id, int shiftId)
	{
		DbRequest<ShiftWorkdayEntry> req = new DbRequest<ShiftWorkdayEntry>(template, "wadm_shift_workday_get", ShiftWorkdayEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, shiftId));
		return req.singleResult();
	}

	@Override
	public List<ShiftWorkdayEntry> getWorkdayList(int shiftId)
	{
		DbRequest<ShiftWorkdayEntry> req = new DbRequest<ShiftWorkdayEntry>(template, "wadm_shift_workday_get_list", ShiftWorkdayEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, shiftId));
	}

	@Override
	public void deleteWorkday(int id)
	{
		DbRequest<ShiftWorkdayEntry> req = new DbRequest<ShiftWorkdayEntry>(template, "wadm_shift_workday_delete", ShiftWorkdayEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	
	//WORKDAY TIME
	@Override
	public void saveWorkdayTime(ShiftWorkdayTimeEntry e)
	{
		DbRequest<ShiftWorkdayTimeEntry> req = new DbRequest<ShiftWorkdayTimeEntry>(template, "wadm_shift_workday_time_save", ShiftWorkdayTimeEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ShiftWorkdayTimeEntry getWorkdayTime(int id, int workdayId)
	{
		DbRequest<ShiftWorkdayTimeEntry> req = new DbRequest<ShiftWorkdayTimeEntry>(template, "wadm_shift_workday_time_get", ShiftWorkdayTimeEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, workdayId));
		return req.singleResult();
	}

	@Override
	public List<ShiftWorkdayTimeEntry> getWorkdayTimeList(int workdayId)
	{
		DbRequest<ShiftWorkdayTimeEntry> req = new DbRequest<ShiftWorkdayTimeEntry>(template, "wadm_shift_workday_time_get_list", ShiftWorkdayTimeEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, workdayId));
	}

	@Override
	public void deleteWorkdayTime(int id)
	{
		DbRequest<ShiftWorkdayTimeEntry> req = new DbRequest<ShiftWorkdayTimeEntry>(template, "wadm_shift_workday_time_delete", ShiftWorkdayTimeEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

	
	//EXCLUDE
	@Override
	public void saveWorkdayExclude(ShiftWorkdayExcludeEntry e)
	{
		DbRequest<ShiftWorkdayExcludeEntry> req = new DbRequest<ShiftWorkdayExcludeEntry>(template, "wadm_shift_workday_exclude_save", ShiftWorkdayExcludeEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, e),
				new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public ShiftWorkdayExcludeEntry getWorkdayExclude(int id, int workdayId)
	{
		DbRequest<ShiftWorkdayExcludeEntry> req = new DbRequest<ShiftWorkdayExcludeEntry>(template, "wadm_shift_workday_exclude_get", ShiftWorkdayExcludeEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.INTEGER, workdayId));
		return req.singleResult();
	}

	@Override
	public List<ShiftWorkdayExcludeEntry> getWorkdayExcludeList(int workdayId)
	{
		DbRequest<ShiftWorkdayExcludeEntry> req = new DbRequest<ShiftWorkdayExcludeEntry>(template, "wadm_shift_workday_exclude_get_list", ShiftWorkdayExcludeEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, workdayId));
	}

	@Override
	public void deleteWorkdayExclude(int id)
	{
		DbRequest<ShiftWorkdayExcludeEntry> req = new DbRequest<ShiftWorkdayExcludeEntry>(template, "wadm_shift_workday_exclude_delete", ShiftWorkdayExcludeEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id));
	}

}
