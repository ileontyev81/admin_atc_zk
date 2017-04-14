package atc.gui.admin.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

import java.util.Optional;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_hourly_entry")
public class QueueHourlyEntry extends SerializedEntity implements PersistentEntity
{

	@FormField
	private String queue;

	@FormField
	private String hour;

	@FormField
	private String total;

	@FormField
	private String answered;

	@FormField
	private String abandoned;

	@FormField
	private String noanswer;

	@FormField
	private String speakTime;

	@FormField
	private String waitAnswer;

	@FormField
	private String waitAbandon;

    @Override
    public Object getId() {
        return Optional.empty();
    }
}