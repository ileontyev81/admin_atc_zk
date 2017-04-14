package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.NotPersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_geo_search_entry")
public class QueueGeoSearchEntry extends SerializedEntity implements NotPersistentEntity
{
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateStart;
	
	@FormField(type=UIType.DATE_TIME)
	private Timestamp dateEnd;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] queue;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] service;
	
	@FormField(type = UIType.SELECT_NO_ITEM)
	private SelectEntity[] region;
}
