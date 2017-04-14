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