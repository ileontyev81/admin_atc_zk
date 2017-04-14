package atc.gui.admin.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.SortableField;
import atc.gui.admin.domain.UdtSqlType;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_operator_report_entry")
public class OperatorReportEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField
	private Integer id;
	
	@FormField
	@SortableField
	private String name;
	
	@FormField
	@SortableField
	private String callCount;
	
	@FormField
	@SortableField
	private String callTime;
	@FormField
	@SortableField
	private String callTimeAvg;
	@FormField
	@SortableField
	private String workTime;
	@FormField
	@SortableField
	private String pauseTime;
	@FormField
	@SortableField
	private String operationTime;
	@FormField
	@SortableField
	private String aftercallTime;
	
	@FormField
	@SortableField
	private String noanswerCount;
}
