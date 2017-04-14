package atc.gui.admin.domain.model.appobjects;

import lombok.*;
import atc.gui.admin.domain.HasId;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("wadm_select_entry")
@ToString(callSuper = false, of = {"label"})
public class SelectEntity extends SerializedEntity implements HasId
{
	private Integer id;

	private String label;

	private Boolean selected;
}