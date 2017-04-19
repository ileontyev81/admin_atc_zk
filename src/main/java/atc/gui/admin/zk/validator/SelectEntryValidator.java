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

package atc.gui.admin.zk.validator;

import atc.gui.admin.domain.MustBeSelected;
import atc.gui.admin.domain.model.appobjects.SelectEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelectEntryValidator implements ConstraintValidator<MustBeSelected, SelectEntity[]>
{

	@Override
	public void initialize(MustBeSelected constraintAnnotation)
	{
		//nothing to do
	}

	@Override
	public boolean isValid(SelectEntity[] values, ConstraintValidatorContext context)
	{
	  if (values == null)
	  {
		  return true;
	  }
	  return hasSelected(values);
	}

	private boolean hasSelected(SelectEntity[] values)
	{
		for (SelectEntity value : values)
		{
			if (value.getSelected())
			{
				return true;
			}
		}
		return false;
	}
}
