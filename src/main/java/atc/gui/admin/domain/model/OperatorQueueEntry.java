package atc.gui.admin.domain.model;

import lombok.Data;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;

import javax.validation.constraints.NotNull;

@Data
@UdtSqlType("wadm_operator_queue_entry")
public class OperatorQueueEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags=FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	private Integer operatorId;
	
	@FormField(type=UIType.SELECT)
	private SelectEntity[] queue;
	
	@FormField
	private Integer penalty;
	
    @FormField(flags=FormFieldFlag.HIDDEN)
    private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private String userLogin;
}
