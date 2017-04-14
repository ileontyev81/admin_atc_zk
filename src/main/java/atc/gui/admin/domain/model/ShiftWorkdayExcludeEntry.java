 package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import org.zkoss.bind.annotation.Immutable;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@UdtSqlType("wadm_shift_workday_exclude_entry")
public class ShiftWorkdayExcludeEntry extends SerializedEntity implements PersistentEntity
{
    @NotNull
	@FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private Integer id;
	
	@FormField
    private String description;
	
	@FormField(type=UIType.DATE)
    private Date date;

    @Immutable
    public Date getDate() {
        return date;
    }

	private int shiftId;
	
	@FormField(type=UIType.SELECT)
	private SelectEntity[] workday;
	
	@FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private Timestamp created;

    @FormField(flags=FormFieldFlag.HIDDEN)
    private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
    private String userLogin;
}
