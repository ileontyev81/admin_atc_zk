package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_provider_entry")
public class ProviderEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField
	private String description;

	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;
	
	@FormField(flags = FormFieldFlag.HIDDEN)
	private Integer userId;
	
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
