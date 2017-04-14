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

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_callerid_entry")
public class CallerIdEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField(flags = FormFieldFlag.HIDDEN)
	private Integer parentId = 0;
	
	@FormField
	private String regex;
	
	@FormField
	private String description;
	
	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;
	
	private Integer userId;
	
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
