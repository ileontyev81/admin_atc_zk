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

package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ProviderEntry;
import atc.gui.admin.domain.model.VariableEntry;

public interface ProviderRepository
{
	public List<ProviderEntry> getProviders();
	public void saveProvider(ProviderEntry provider);
	public void removeProvider(int providerId);
	public ProviderEntry getProvider(int providerId);

	
	public List<VariableEntry> getVariables(int providerId);
	public void saveVariable(VariableEntry var);
	public void removeVariable(int varId);
	public VariableEntry getVariable(int varId, int parentId);
}
