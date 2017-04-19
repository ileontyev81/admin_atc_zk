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

package atc.gui.admin.infrastructure.service.udt;

import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.domain.model.*;
import atc.gui.admin.domain.model.appobjects.SelectEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddUserTypesAspect
{
	private List<Class<? extends SerializedEntity>> entryDaos = new ArrayList<Class<? extends SerializedEntity>>();

	public AddUserTypesAspect()
	{
		entryDaos.add(AccountEntry.class);
		entryDaos.add(ActionEntry.class);
		//entryDaos.add(ActionAccountEntry.class);
		//entryDaos.add(ActionCallerIdEntry.class);
		entryDaos.add(DirectionEntry.class);
		//entryDaos.add(ActionIpAddressEntry.class);
		entryDaos.add(ProviderEntry.class);
		entryDaos.add(ExtensionEntry.class);
		entryDaos.add(AccessPermissionEntity.class);
		entryDaos.add(AccessRoleEntry.class);
		entryDaos.add(UserEntry.class);
		entryDaos.add(ExtensionActionEntry.class);
		entryDaos.add(QueueEntry.class);
		entryDaos.add(CDREntry.class);
		entryDaos.add(ChannelEntry.class);
		entryDaos.add(PeerEntry.class);
		//entryDaos.add(LogEntry.class);
		entryDaos.add(SelectEntity.class);
	}
	
	public void addUserTypes(Connection returnedConnection) throws SQLException
    {
		for (Class<?> daoWrapperClass : entryDaos)
		{
		    String sqlTypeName = daoWrapperClass.getAnnotation(UdtSqlType.class).value();
		    returnedConnection.getTypeMap().put(sqlTypeName, daoWrapperClass);
		}
    }
	
}
