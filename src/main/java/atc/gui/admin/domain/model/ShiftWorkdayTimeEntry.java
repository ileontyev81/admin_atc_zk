package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import org.zkoss.bind.annotation.Immutable;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@UdtSqlType("wadm_shift_workday_time_entry")
public class ShiftWorkdayTimeEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private Integer id;

	private int workdayId;
	
	@FormField(type=UIType.TIME)
	private Time timeFrom;

    @Immutable
    public Time getTimeFrom() {
        return timeFrom;
    }

	@FormField(type=UIType.TIME)
	private Time timeTo;

    @Immutable
    public Time getTimeTo() {
        return timeTo;
    }

	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private Timestamp created;

    @FormField(flags=FormFieldFlag.HIDDEN)
    private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private String userLogin;
}
