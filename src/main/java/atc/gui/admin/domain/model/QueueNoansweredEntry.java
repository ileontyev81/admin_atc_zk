package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_noanswered_entry")
public class QueueNoansweredEntry extends SerializedEntity implements PersistentEntity
{
	@FormField
	private Long id;

	@FormField
	private String queue;
	
	@FormField
	private String member;
	
	@FormField
	private String uniqueid;
	
	@FormField(type=UIType.DATE_TIME)
	private Timestamp created;
	
	@FormField
	private String ringTime;
	
	@Override
	public boolean isPersisted()
	{
		return true;
	}
}
