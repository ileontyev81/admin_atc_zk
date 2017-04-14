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
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_entry")
public class QueueEntry extends SerializedEntity implements PersistentEntity
{
    @NotNull
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE | FormFieldFlag.ADD_VISIBLE)
	@NotBlank
	private String name;
	
	@FormField
	@NotBlank
	private String description;
	
	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] strategy;

	@FormField(type = UIType.SELECT_NO_ITEM)
	private SelectEntity[] context;
	
	@FormField
	private String musicOnHold;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private String announce;
	
	//@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	//private Integer announceFrequency;
	
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private String periodic;
	
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private Integer periodicFrequency;
	
	@FormField
	private Integer timeout;

	@FormField
	private Integer callRetry;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private Integer weight;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private Integer wrapupTime;

	@FormField
	private Integer maxLength;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private Integer memberDelay;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private Integer serviceLevel;

	@FormField(type = UIType.LIST)
	private SelectEntity[] joinEmpty;

	@FormField(type = UIType.LIST)
	private SelectEntity[] leaveWhenEmpty;

	@NotNull
	@FormField(type = UIType.CHECKBOX)
	private Boolean ringInUse;

	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;

	private Integer userId;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}