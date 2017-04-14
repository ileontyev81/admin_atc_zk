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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_operator_entry")
public class OperatorEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags=FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField
	@NotBlank
	private String name;
	
	@FormField
	@NotBlank
	@Email
	private String email;
	
	@FormField
	@NotBlank
	private String login;
	
	@FormField(type=UIType.PASSWORD, flags=FormFieldFlag.HIDDEN|FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.ADD_VISIBLE)
	@NotBlank
	private String password;

	@FormField(type=UIType.SELECT_NO_ITEM)
	private SelectEntity[] shift;
	
	@FormField(type=UIType.SELECT)
	private SelectEntity[] account;
	
	@FormField(type=UIType.LIST)
	private SelectEntity[] service;
	
	/*@FormField(type=UIType.LIST)
	private SelectEntity[] group;*/
	@FormField(type=UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] group;
	
	@FormField(type=UIType.LIST, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private SelectEntity[] queue;
	
	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private Timestamp created;

    @FormField(flags=FormFieldFlag.HIDDEN)
    private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private String userLogin;
}
