package atc.gui.admin.domain.model;

import atc.gui.admin.domain.DbField;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@UdtSqlType("wadm_queue_member_entry")
public class QueueMemberEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Integer id;

	private Integer queueId;

	@FormField
	private Integer seq;
	
	@FormField
	private String description;

	@FormField(type = UIType.SELECT_NO_ITEM)
	private SelectEntity[] account;
	
	@FormField
	@NotBlank
	@DbField(value = "interface")
	private String memberInterface;

	@FormField
	private Integer penalty;

	@NotNull
	@FormField(type=UIType.CHECKBOX)
	private Boolean active;

	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;

	@FormField(flags = FormFieldFlag.HIDDEN)
	private Integer userId;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String userLogin;

}
