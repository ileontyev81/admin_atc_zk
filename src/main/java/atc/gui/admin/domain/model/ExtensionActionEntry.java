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
@UdtSqlType("wadm_extension_action_entry")
public class ExtensionActionEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField
	private Integer seq;
		
	private Integer extensionId;
	
	@FormField
	private String description;
	
	//private Integer actionId;
	@FormField(type=UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] script;

	@FormField(type=UIType.SELECT_NO_ITEM)
	private SelectEntity[] provider;
	
	@FormField(type=UIType.SELECT_NO_ITEM)
	private SelectEntity[] shift;
	
	//@FormField(type=UIType.LIST, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	@FormField(type=UIType.LIST)
	private SelectEntity[] service;

	@FormField(type=UIType.LIST)
	private SelectEntity[] context;

	@FormField(type=UIType.CHECKBOX)
    private Boolean activeChannelRequired;
	
	@FormField(type=UIType.CHECKBOX)
    private Boolean huntStop;
	
	@FormField(type=UIType.CHECKBOX)
	private Boolean active;
	
	/*@FormField(type=UIType.CODEBOX)
	private Boolean conditionScript;*/

	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;
	
	private Integer userId;
	
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
