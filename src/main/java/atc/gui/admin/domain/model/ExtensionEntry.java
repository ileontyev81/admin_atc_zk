package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_extension_entry")
public class ExtensionEntry extends SerializedEntity implements PersistentEntity
{
    @NotNull
	@FormField(flags=FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@NotBlank
    @FormField
	private String description;
   
	@FormField(type=UIType.SELECT_NO_ITEM, flags=FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.HIDDEN)
	private SelectEntity[] noactionScript;
	
	@FormField(type=UIType.SELECT_NO_ITEM, flags=FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.HIDDEN)
	private SelectEntity[] preactionScript;
	
    @FormField(type=UIType.LIST)
	private SelectEntity[] context;
    
    @FormField(type=UIType.LIST)
	private SelectEntity[] service;
    
    @FormField
	private Integer seq;

	@NotNull
    @FormField(type=UIType.CHECKBOX)
	private Boolean active;
    
    @FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.LIST_VISIBLE)
   	private Timestamp created;

    @NotNull
    @FormField(flags=FormFieldFlag.HIDDEN)
	private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
