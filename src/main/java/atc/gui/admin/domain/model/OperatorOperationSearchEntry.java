package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_operator_opearation_search_entry")
public class OperatorOperationSearchEntry extends SerializedEntity implements HasExport, NotPersistentEntity
{
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateStart;
	
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateEnd;
	
	//@FormField(type = UIType.LIST)
	//private SelectEntity[] operator;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] group;
	
	@FormField
	private Integer aftercallTimeFrom;

	@NotNull
	@FormField(type=UIType.CHECKBOX)
	private Boolean export;
	
	@Override
	public Boolean isExportEnabled()
	{
		return export;
	}
}
