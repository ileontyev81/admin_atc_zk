package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=true)
@UdtSqlType("wadm_script_entry")
public class ScriptEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Integer id;

	@FormField
	@NotBlank
	private String tag;
	
	@FormField
	private String description;

	@FormField(type = UIType.CODEBOX, flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private String body;

	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;

	@FormField(flags = FormFieldFlag.HIDDEN)
	private Integer userId;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}