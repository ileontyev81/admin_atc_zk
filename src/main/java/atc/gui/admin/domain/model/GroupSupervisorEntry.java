package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_group_supervisor_entry")
public class GroupSupervisorEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags=FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField(type=UIType.SELECT)
	private SelectEntity[] supervisor;
	
	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.LIST_VISIBLE)
   	private Timestamp created;
    
	@FormField(flags=FormFieldFlag.HIDDEN)
	private Integer groupId = 0;
	
    @FormField(flags=FormFieldFlag.HIDDEN)
	private Integer userId = 0;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}