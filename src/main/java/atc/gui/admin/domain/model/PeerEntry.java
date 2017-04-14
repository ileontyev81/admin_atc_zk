package atc.gui.admin.domain.model;

import atc.gui.admin.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_peer_entry")
public class PeerEntry extends SerializedEntity implements HasId, PersistentEntity
{
	@NotNull
	@FormField
	private Integer id;

}