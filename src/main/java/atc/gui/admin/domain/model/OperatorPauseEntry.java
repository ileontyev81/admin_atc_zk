package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.HasExport;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.SortableField;
import atc.gui.admin.domain.UdtSqlType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_operator_pause_entry")
public class OperatorPauseEntry extends SerializedEntity implements PersistentEntity, HasExport
{
	@FormField
	private Long id;
	
	@FormField
	@SortableField
	private String operator;
	
	@FormField
	@SortableField
	private String type;
	
	@FormField
	@SortableField
	private String created;
	
	@FormField
	@SortableField
	private String length;
	
	@Override
	public Boolean isExportEnabled()
	{
		return false;
	}
}
