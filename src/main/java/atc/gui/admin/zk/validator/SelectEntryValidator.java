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
