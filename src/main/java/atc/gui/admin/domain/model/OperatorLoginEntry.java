package atc.gui.admin.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_operator_login_entry")
public class OperatorLoginEntry extends SerializedEntity implements PersistentEntity
{
	@FormField
	private Long id;
	
	@FormField
	private String login;
	
	@FormField
	private String operator;
	
	@FormField
	private String group;
	
	@FormField
	private String timeIn;
	
	@FormField
	private String timeOut;
	
	@FormField
	private String length;

	@FormField
	private String reason;
}
