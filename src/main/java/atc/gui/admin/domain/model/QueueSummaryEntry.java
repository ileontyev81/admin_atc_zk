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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_queue_summary_entry")
public class QueueSummaryEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField
	private Integer id;

	@FormField
	private String name;
	
	@FormField
	private String description;

	@FormField
	private String total;
	
	@FormField
	private String answered;
	
	@FormField
	private String abandoned;
	
	@FormField
	private String noanswer;
	
	@FormField
	private String operators;
	
	@FormField
	private String speakTime;
	
	@FormField
	private String waitAnswer;
	
	@FormField
	private String waitAbandon;
	
}
