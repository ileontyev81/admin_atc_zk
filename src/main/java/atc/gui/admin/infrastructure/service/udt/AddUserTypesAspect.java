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
