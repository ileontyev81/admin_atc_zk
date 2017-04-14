package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_variable_entry")
public class VariableEntry extends SerializedEntity implements PersistentEntity
{
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	private Integer parentId;
	
	@NotBlank
	@FormField
	private String name;
	
	@NotBlank
	@FormField
	private String value;
	
	//@NotBlank
	/*@FormField
	private Integer priority;
	*/
	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;
	
	private Integer userId;
	
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
