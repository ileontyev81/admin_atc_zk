package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.zk.UIType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@UdtSqlType("wadm_cdr_route_entry")
public class CDRRouteEntry extends SerializedEntity implements RecordHolder, PersistentEntity
{
	@NotNull
	@FormField
	private Long callId;

	public Long getId() {
		return callId;
	}

	@FormField
	private String uniqueid;

	@FormField(type = UIType.DATE_TIME)
	private Timestamp created;

	@FormField
	private String callerid;

	@FormField
	private String extension;

	@FormField
	private String service;

	@FormField
	private String context;

	@FormField
	private String account;

	@FormField(type = UIType.AUDIO)
	private String record;

}
