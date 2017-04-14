package atc.gui.admin.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_summary_entry")
public class QueueSummaryEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField
	private Integer id;

	@FormField
	private String name;
	
	@FormField
	private String description;

	@FormField
	private String total;
	
	@FormField
	private String answered;
	
	@FormField
	private String abandoned;
	
	@FormField
	private String noanswer;
	
	@FormField
	private String operators;
	
	@FormField
	private String speakTime;
	
	@FormField
	private String waitAnswer;
	
	@FormField
	private String waitAbandon;
	
}
