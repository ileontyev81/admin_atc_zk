package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_raw_entry")
public class QueueRawEntry extends SerializedEntity implements PersistentEntity
{
	@FormField
	private Long id;

	@FormField
	private String event;
	
	@FormField
	private String queue;
	
	@FormField
	private String agent;
	
	@FormField
	private String data1;
	
	@FormField
	private String data2;
	
	@FormField
	private String data3;
	
	@FormField
	private String data4;
	
	@FormField
	private String data5;
	
	@FormField
	private Timestamp created;
	
	@FormField
	private String uniqueid;
}
