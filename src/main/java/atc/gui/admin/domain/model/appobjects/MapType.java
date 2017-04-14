package atc.gui.admin.domain.model.appobjects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.ArrayUtils;

import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("map_type")
public class MapType extends SerializedEntity 
{
	private MapPair[] entryarray;
	
	public void addEntryPair(MapPair entry)
	{
		entryarray = ArrayUtils.add(entryarray, entry);
	}
}
