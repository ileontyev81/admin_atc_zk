/*
 * Copyright (C) 2017 i.leontyev81@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
