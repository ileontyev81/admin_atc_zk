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

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("wadm_access_role_entry")
public class AccessRoleEntry extends SerializedEntity implements PersistentEntity 
{
    @NotNull
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private Integer id;

    @NotNull
	@FormField
    private String name;

	@FormField
    private String description;

	@FormField(type=UIType.LIST)
	private SelectEntity[] permission;// type small enough not to be SelectEntry[] 
}
