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

package atc.gui.admin.infrastructure.dao;

import atc.gui.admin.domain.HasInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlParameterValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DbRequest<T extends HasInit>
{
    private final String procedureName;
    private final JdbcTemplate jdbcTemplate;
    private final Class<T> entityType;
    
    private List<T> lastReturnedData = null;
    
    public DbRequest(JdbcTemplate jdbcTemplate, String procedureName, Class<T> entityType)
    {
    	this.jdbcTemplate = jdbcTemplate;
    	this.procedureName = procedureName;
    	this.entityType = entityType;
    }
    
    public List<T> execute(final SqlParameterValue...parameters)
    {
        return jdbcTemplate.execute(
            new PreparedStatementCreator()
            {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
                {
                    StringBuffer sqlBuffer = new StringBuffer();
                    sqlBuffer.append("SELECT * from ").append(procedureName);
                    sqlBuffer.append("(");
                    for (int i = 0; i < parameters.length; i++)
                    {
                        sqlBuffer.append("?");
                        if (i != (parameters.length - 1))
                        {
                            sqlBuffer.append(",");
                        }
                    }
                    sqlBuffer.append(")");
                    log.info("dbrequest sql: " + sqlBuffer.toString());
                    PreparedStatement statement = connection.prepareStatement(sqlBuffer.toString());
                    for (int j = 0; j < parameters.length; j++)
                    {
                        statement.setObject(j + 1, parameters[j].getValue(), parameters[j].getSqlType());
                        log.info(String.format("parameter №%d value=%s sqlType=%d", j + 1, parameters[j].getValue().toString(), parameters[j].getSqlType()));
                    }
                    return statement;
                }

            },
            new PreparedStatementCallback<List<T>>() 
            {
                @Override
                public List<T> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException 
                {
                	lastReturnedData = new ArrayList<T>();
                	ResultSet rs = ps.executeQuery();
                	if (rs.getMetaData().getColumnCount() > 1)
                	{
                		try 
                		{
                			while (rs.next())
                			{
                			    T entity = entityType.newInstance();
                			    entity.init(rs);
                			    lastReturnedData.add((T) entity);
                			}
                		}
                		catch (Exception exception) 
                		{
                			throw new RuntimeException(exception);
                		}
                	}
                    return lastReturnedData;
                }
            }
        );
    }
 
    public T singleResult()
    {
    	if (hasResult())
    	{
    		return lastReturnedData.get(0);
    	}
    	return null;
    }
    
    public List<T> result()
    {
    	return lastReturnedData;
    }
    
    public boolean hasResult()
    {
    	return !lastReturnedData.isEmpty();
    }
    
}