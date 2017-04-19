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
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_user_entry")
public class UserEntry extends SerializedEntity implements PersistentEntity, UserDetails
{
	@NotNull
	private Integer id;

	@NotBlank
	@FormField
	private String login;

    @NotBlank
    @FormField(type=UIType.PASSWORD, flags = FormFieldFlag.HIDDEN | FormFieldFlag.ADD_VISIBLE | FormFieldFlag.EDIT_VISIBLE)
	private String password;

	@FormField
	private String firstname;
	
	@FormField
	private String lastname;

    // TODO: TEMP, REMOVE
//	@NotNull
//	@FormField(type = UIType.CHECKBOX)
//	private Boolean expired;

	@NotNull
	@FormField(type = UIType.CHECKBOX)
	private Boolean blocked;// cannot be authenticated until admin unblock

    // TODO: TEMP, REMOVE
//	@NotNull
//	@FormField(type = UIType.CHECKBOX)
//	private boolean credentialsExpired;// cannot be authenticated until create new credentials

	@FormField(type = UIType.DATE_TIME, flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Timestamp created;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] service;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] queue;
	
	@FormField(type = UIType.LIST)
	private SelectEntity[] group;

	@NotEmpty
	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] role;
	/*@FormField(type = UIType.TEXTAREA, flags = FormFieldFlag.HIDDEN | FormFieldFlag.EDIT_VISIBLE)
	private String address;
	*/

	@Transient
	private Collection<? extends GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAuthorities(Collection authorities)
	{
		this.authorities.addAll(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	@Override
	public String getUsername()
	{
		return login;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		//return !expired;
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return !blocked;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		//return !credentialsExpired;
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return !blocked;
	}
}
