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

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_action_entry")
public class ActionEntry extends SerializedEntity implements PersistentEntity  
{
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField
	private String description;
	
	//@FormField
	//private Integer seq;
	

    
    //@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)	
	//private Integer parentActionId = 0;
    //@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)	
    //private String parentAction;
    
    @FormField(type=UIType.SELECT_NO_ITEM)
	private SelectEntity[] provider;
    
    @FormField(type=UIType.SELECT)
	private SelectEntity[] runtimeScript;
    
    //@FormField(type=UIType.LIST)
	//private SelectEntity[] completeType;
    //@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.ADD_VISIBLE)
	//private String completeScript;
    

    
    /*@FormField(type=UIType.LIST)
	private SelectEntity[] context;
    
    @FormField(type=UIType.LIST)
    private SelectEntity[] service;
    */
    
    /*@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.ADD_VISIBLE)
	private String extensionExpression;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.ADD_VISIBLE)
    private String extensionReplace;
	*/
    
    
    @FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;
    
    @FormField(flags=FormFieldFlag.HIDDEN)
	private Integer userId = 0;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private String userLogin;
}
