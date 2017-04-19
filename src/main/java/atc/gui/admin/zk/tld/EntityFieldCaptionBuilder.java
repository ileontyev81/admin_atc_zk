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

package atc.gui.admin.zk.tld;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.util.resource.Labels;

import atc.gui.admin.domain.UdtSqlType;

class EntityFieldCaptionBuilder<T>
{
	private final String ENTITY_FIELD_CAPTION_PATTERN = "view.%s.field";

	private final String currentTypeId;
	
	public EntityFieldCaptionBuilder(Class<T> entityType)
	{
		this.currentTypeId = entityType.getAnnotation(UdtSqlType.class).value();
	}
	
    public String getFieldCaption(String fieldName)
    {
        String fieldKey = fieldKey(String.format(ENTITY_FIELD_CAPTION_PATTERN, currentTypeId), fieldName);
        String label = Labels.getLabel(fieldKey);
        return (label == null) ? fieldName : label;
    }

    public String fieldKey(String prefix, String fieldName)
    {
        StringBuffer result = new StringBuffer();
        result.append(prefix).append(".");
        String[] splitted = StringUtils.splitByCharacterTypeCamelCase(fieldName);
        if (splitted.length > 1)
        {
            for (int i = 0; i < splitted.length; i++)
            {
                String namePart = splitted[i];
                result.append(namePart.toLowerCase());
                if (i < (splitted.length - 1))
                {
                    result.append("_");
                }
            }
        }
        else 
        {
            result.append(fieldName);
        }
        return result.toString();
    }
    
}
