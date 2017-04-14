package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_raw_search_entry")
public class QueueRawSearchEntry extends SerializedEntity implements HasExport, NotPersistentEntity
{
	/*@FormField(type=UIType.DATE_TIME)
	private Timestamp dateStart;
	
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateEnd;
	*/
	@FormField
	@NotBlank
	private String uniqueid;

	@NotNull
	@FormField(type=UIType.CHECKBOX)
	private Boolean export;
	
	@Override
	public Boolean isExportEnabled()
	{
		return export;
	}
}
