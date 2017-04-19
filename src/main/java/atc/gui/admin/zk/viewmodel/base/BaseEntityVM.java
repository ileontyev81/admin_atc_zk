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

package atc.gui.admin.zk.viewmodel.base;

import lombok.Getter;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import atc.gui.admin.zk.transition.impl.EditPathPart;
import atc.gui.admin.domain.PersistentEntity;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Slf4j
public abstract class BaseEntityVM<T extends PersistentEntity> extends BaseVM
{
	@Getter @Setter
	private T entity;
	
	@NotifyChange("entity")
	@Init
	public void baseEntityInit()
	{
        log.debug("BaseEntityVM before loadEntity");
		entity = loadEntity(getTransitionManager().getParamValueInTop(EditPathPart.OBJECT_ID));
	}
	
	@Command("submit")
	public void submit() 
	{
		saveEntity(entity);
        getTransitionManager().back();
	}
	
    protected abstract T loadEntity(String entityId);
    
    protected abstract void saveEntity(T entity);
    
}

