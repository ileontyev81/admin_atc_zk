package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_channel_entry")
public class ChannelEntry extends SerializedEntity implements HasId, PersistentEntity
{
	@NotNull
	@FormField
	private Integer id;

}