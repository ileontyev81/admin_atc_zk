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
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@UdtSqlType("wadm_extension_entry")
public class ExtensionEntry extends SerializedEntity implements PersistentEntity
{
    @NotNull
	@FormField(flags=FormFieldFlag.LIST_VISIBLE)
	private Integer id;
	
	@NotBlank
    @FormField
	private String description;
   
	@FormField(type=UIType.SELECT_NO_ITEM, flags=FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.HIDDEN)
	private SelectEntity[] noactionScript;
	
	@FormField(type=UIType.SELECT_NO_ITEM, flags=FormFieldFlag.EDIT_VISIBLE|FormFieldFlag.HIDDEN)
	private SelectEntity[] preactionScript;
	
    @FormField(type=UIType.LIST)
	private SelectEntity[] context;
    
    @FormField(type=UIType.LIST)
	private SelectEntity[] service;
    
    @FormField
	private Integer seq;

	@NotNull
    @FormField(type=UIType.CHECKBOX)
	private Boolean active;
    
    @FormField(type = UIType.DATE_TIME, flags=FormFieldFlag.LIST_VISIBLE)
   	private Timestamp created;

    @NotNull
    @FormField(flags=FormFieldFlag.HIDDEN)
	private Integer userId;
    
    @FormField(flags=FormFieldFlag.HIDDEN|FormFieldFlag.LIST_VISIBLE)
	private String userLogin;
}
