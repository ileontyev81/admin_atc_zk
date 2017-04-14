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
@UdtSqlType("wadm_queue_abandoned_entry")
public class QueueAbandonedEntry extends SerializedEntity implements PersistentEntity
{
	@FormField
	private Long id;

	@FormField
	private String queue;
	
	@FormField
	private String uniqueid;
	
	@FormField(type=UIType.DATE)
	private Timestamp created;
	
	@FormField
	private String callerid;
	
	@FormField
	private String enterPosition;
	
	@FormField
	private String leavePosition;
	
	@FormField
	private String waitTime;
	
}
