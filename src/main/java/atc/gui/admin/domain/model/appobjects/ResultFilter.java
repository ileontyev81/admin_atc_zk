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

package atc.gui.admin.domain.model.appobjects;

import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;

@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("result_filter")
public class ResultFilter extends SerializedEntity 
{
    public enum Order
    {
        ASC("ASC"), DESC("DESK");
        
        private String value = null;
        
        Order(String value)
        {
            this.value = value;
        }
        
        String getValue()
        {
            return value;
        }
    }
    
    @Transient
    private static int LIMIT_UNUSED = -1;// converted into NULL to be 'no influence'
    @Transient
    private static int OFFSET_UNUSED = 0;// no influence
    
    private int limit = LIMIT_UNUSED; 
    private int offset = OFFSET_UNUSED;
	private MapType regex = new MapType(); 
	private MapType ordering = new MapType();  
	private boolean count = false; 
	
	public ResultFilter(Integer limit, Integer offset)
	{
	    this.limit = limit;
	    this.offset = offset;
	}
	
	public void addRegex(String field, String expression)
	{
		regex.addEntryPair(new MapPair(field, expression));
	}
	
	public void addOrder(String field, Order order)
	{
	    ordering.addEntryPair(new MapPair(field, order.getValue()));
	}
	
	public ResultFilter withNoLimit()
	{
	    this.limit = LIMIT_UNUSED; 
	    this.offset = OFFSET_UNUSED;
	    return this;
	}

}