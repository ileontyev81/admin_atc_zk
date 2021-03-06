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

import atc.gui.admin.domain.HasId;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("wadm_access_permission_entry")
public class AccessPermissionEntity extends SerializedEntity implements HasId, GrantedAuthority, PersistentEntity
{
    @NotNull
    private Integer id;

    @NotBlank
    public String regexp;

    @NotNull
    public String description;

    @Override
    public String getAuthority() 
    {
        return regexp;
    }
}

