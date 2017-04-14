package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.MustBeSelected;
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
@UdtSqlType("wadm_service_linked_entry")
public class ServiceLinkedEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	private Integer parentId;
	
	@FormField(type=UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] service;
	
	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;

    @NotNull
	@FormField(flags=FormFieldFlag.HIDDEN)
	private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private String userLogin;
}
