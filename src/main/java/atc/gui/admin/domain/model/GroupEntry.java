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
@UdtSqlType("wadm_group_entry")
public class GroupEntry extends SerializedEntity implements PersistentEntity
{
	@FormField(flags=FormFieldFlag.LIST_VISIBLE)
	@NotNull
	private Integer id;

	@NotBlank
    @FormField
	private String name;

	@NotBlank
    @FormField
	private String description;
	
	@FormField(type=UIType.LIST)
	private SelectEntity[] queue;
	
	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.LIST_VISIBLE)
   	private Timestamp created;
    
    @FormField(flags=FormFieldFlag.HIDDEN)
	private Integer userId;

	@NotNull
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
