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

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_service_entry")
public class ServiceEntry extends SerializedEntity implements PersistentEntity
{
    @NotNull
	@FormField(flags = FormFieldFlag.LIST_VISIBLE)
	private Integer id;

	@FormField
	@NotBlank
	private String ident;

	@FormField
	@NotBlank
	private String description;

	@FormField
	private String content_url;

	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;

	@NotNull
	@FormField(type=UIType.CHECKBOX)
	private Boolean active;
	
	@FormField(flags = FormFieldFlag.HIDDEN)
	private Integer userId;
	
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
