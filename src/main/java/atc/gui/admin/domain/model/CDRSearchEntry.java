package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("wadm_cdr_core_search_entry")
public class CDRSearchEntry extends SerializedEntity implements HasExport, NotPersistentEntity
{
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateStart;
	
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateEnd;
	
	@FormField
	private String srcRegex;
	
	@FormField
	private String dstRegex;
	
	@FormField
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
