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

import org.hibernate.validator.constraints.NotBlank;

import atc.gui.admin.domain.DbField;
import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.FormFieldFlag;
import atc.gui.admin.domain.MustBeSelected;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.domain.model.appobjects.SelectEntity;
import atc.gui.admin.zk.UIType;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@UdtSqlType("wadm_account_entry")
public class AccountEntry extends SerializedEntity implements PersistentEntity
{
	@NotNull
	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private Integer id;

	@FormField
	@NotBlank
	private String name;

	@FormField(type = UIType.PASSWORD, flags = FormFieldFlag.HIDDEN | FormFieldFlag.EDIT_VISIBLE | FormFieldFlag.ADD_VISIBLE)
	@NotBlank
	private String secret;

	@FormField
	private String callerid;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.EDIT_VISIBLE)
	private String callgroup;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.EDIT_VISIBLE)
	private String pickupgroup;

	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] context;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.EDIT_VISIBLE)
	@NotBlank
	private String host;

	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] nat;

	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] qualify;

	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] type;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	// @NotNull
	// @Pattern(regexp="^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
	private String ipaddr;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String lastms;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String useragent;

	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] service;

	@FormField(type = UIType.LIST)
	@DbField(value="codec_disallow")
	private SelectEntity[] codecDisallow;// codec_disallow

	@FormField(type = UIType.LIST)
	@MustBeSelected
	@DbField(value="codec_allow")
	private SelectEntity[] codecAllow;// codec_allow

	@FormField(type = UIType.SELECT)
	@MustBeSelected
	private SelectEntity[] dtmf;

	private Integer userId;

	@FormField(flags = FormFieldFlag.HIDDEN | FormFieldFlag.LIST_VISIBLE)
	private String userLogin;

}