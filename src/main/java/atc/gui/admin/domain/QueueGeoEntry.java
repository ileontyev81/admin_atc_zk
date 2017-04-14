package atc.gui.admin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_geo_entry")
public class QueueGeoEntry extends SerializedEntity
{
	@FormField
	private String region;
	
	@FormField
	private String answered;
	
	@FormField
	private String speakTime;
	
	@FormField
	private String waitTime;
}
