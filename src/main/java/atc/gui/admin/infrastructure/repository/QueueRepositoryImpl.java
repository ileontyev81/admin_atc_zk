package atc.gui.admin.infrastructure.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.domain.model.QueueEntry;
import atc.gui.admin.domain.model.QueueMemberEntry;
import atc.gui.admin.domain.service.repository.QueueRepository;
import atc.gui.admin.infrastructure.dao.DbRequest;

@Repository("queueRepository")
public class QueueRepositoryImpl implements QueueRepository
{
	private JdbcTemplate template;
	
	@Autowired
	public QueueRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}

	@Override
	public List<QueueEntry> getQueues()
	{
		DbRequest<QueueEntry> req = new DbRequest<QueueEntry>(template, "wadm_queue_get_list", QueueEntry.class);
		return req.execute();
	}

	@Override
	public void saveQueue(QueueEntry queue)
	{
		DbRequest<QueueEntry> req = new DbRequest<QueueEntry>(template, "wadm_queue_save", QueueEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, queue),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void removeQueue(int queueId)
	{
		DbRequest<QueueEntry> req = new DbRequest<QueueEntry>(template, "wadm_queue_delete", QueueEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, queueId));
	}

	@Override
	public QueueEntry getQueue(int queueId)
	{
		DbRequest<QueueEntry> req = new DbRequest<QueueEntry>(template, "wadm_queue_get", QueueEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, queueId));
		return req.singleResult();
	}

	
	
	
	
	@Override
	public List<QueueMemberEntry> getMembers(int queueId)
	{
		DbRequest<QueueMemberEntry> req = new DbRequest<QueueMemberEntry>(template, "wadm_queue_member_get_list", QueueMemberEntry.class);
		return req.execute(new SqlParameterValue(Types.INTEGER, queueId));
	}

	@Override
	public QueueMemberEntry getMember(int id, int queueId)
	{
		DbRequest<QueueMemberEntry> req = new DbRequest<QueueMemberEntry>(template, "wadm_queue_member_get", QueueMemberEntry.class);
		req.execute(new SqlParameterValue(Types.INTEGER, id), new SqlParameterValue(Types.OTHER, queueId));
		return req.singleResult();
	}

	@Override
	public void saveMember(QueueMemberEntry entry)
	{
		DbRequest<QueueMemberEntry> req = new DbRequest<QueueMemberEntry>(template, "wadm_queue_member_save", QueueMemberEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, entry),
							  new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}

	@Override
	public void deleteMember(int id)
	{
		DbRequest<QueueMemberEntry> req = new DbRequest<QueueMemberEntry>(template, "wadm_queue_member_delete", QueueMemberEntry.class);
		req.execute(new SqlParameterValue(Types.OTHER, id), new SqlParameterValue(Types.INTEGER, SpringSecurityHelper.getCurrentUser().getId()));
	}
	
}
