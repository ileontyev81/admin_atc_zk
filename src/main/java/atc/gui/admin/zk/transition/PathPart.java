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

package atc.gui.admin.zk.transition;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@EqualsAndHashCode(of={"id", "params"})
public class PathPart
{
	@Getter	@Setter
    private String id = "";
    
    private Map<String, String> params = new LinkedHashMap<String, String>();

    public PathPart(PathPart pathPart)
    {
    	this(pathPart.id, pathPart.params);
    }

    public PathPart(String id)
    {
    	this(id, new HashMap<String, String>());
    }
    
    public PathPart(String id, String paramName, String paramValue) 
    {
    	this.id = id;
    	this.params.put(paramName, paramValue);
    }
    
    public PathPart(String id, Map<String, String> params) 
    {
    	this.id = id;
    	this.params.putAll(params);
    }
    
    public boolean hasParam(String paramName) 
    {
    	if (params == null)
    		return false;
    	return params.containsKey(paramName);
    }
    
    public boolean hasNotNullParam(String paramName) 
    {
    	if (params == null)
    		return false;
    	return params.containsKey(paramName) && (getValue(paramName) != null);
    }

    public int getParamsCount()
    {
    	return params.size();
    }

    public Iterator<String> getNamesIterator()
    {
    	if (params != null)
    	{
    		return params.keySet().iterator();
    	}
    	return Collections.emptyIterator();
    }

    public String getValue(String paramName)
    {
    	if (params == null)
    		return null;
    	return params.get(paramName);
    }

	public Integer getIntValue(String paramName)
	{
		if (hasNotNullParam(paramName))
		{
			return Integer.valueOf(getValue(paramName));
		}
		return null;
	}
    
    public String asString()
    {
		return (new Gson()).toJson(this).replaceAll("\"", "");
    }
	
	public boolean hasParamValue(String paramName, String value) 
	{
		return params.containsKey(paramName) && (params.get(paramName) == value);
	}
	
}
