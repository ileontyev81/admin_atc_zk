package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@UdtSqlType("wadm_cdr_route_search_entry")
public class CDRRouteSearchEntry extends SerializedEntity implements HasExport, NotPersistentEntity
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
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] context;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] service;
	
	@NotNull
	@FormField(type=UIType.CHECKBOX)
	private Boolean export;
	
	@Override
	public Boolean isExportEnabled()
	{
		return export;
	}

}
