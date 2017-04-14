package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.RecordHolder;
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
@UdtSqlType("wadm_queue_answered_entry")
public class QueueAnsweredEntry extends SerializedEntity implements PersistentEntity, RecordHolder
{
	@FormField
	private Long id;

	@FormField
	private Long callId;

	@FormField
	private String queue;
	
	@FormField
	private String service;

	@FormField
	private String member;

	@FormField
	private String uniqueid;

	@FormField(type = UIType.DATE_TIME)
	private Timestamp created;

	@FormField
	private String callerid;

	@FormField
	private String waitTime;

	@FormField
	private String speakTime;

	@FormField
	private String enterPosition;

	@FormField
	private String info;

	@FormField(type = UIType.AUDIO)
	private String record;

}
