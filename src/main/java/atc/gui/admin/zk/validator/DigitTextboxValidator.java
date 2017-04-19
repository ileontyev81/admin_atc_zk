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

import atc.gui.admin.zk.validator.form.ValidationUnit;
import atc.gui.admin.zk.validator.form.factory.ValidationUnitFactory;
import org.apache.commons.lang3.math.NumberUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import java.text.MessageFormat;

public class DigitTextboxValidator extends AbstractValidator
{
	private final String errorMessage = Labels.getLabel("message.digit.validator.error");

	private int digitsCount;

    public DigitTextboxValidator(int digitsCount)
    {
		this.digitsCount = digitsCount;
    }

    public void validate(ValidationContext ctx)
    {
    	Property property = ctx.getProperty();
        ValidationUnit<String> validationUnit = ValidationUnitFactory.<String>construct(property.getProperty(), property.getBase());
        if (validationUnit.hasValue() && isValidDigit(validationUnit.getValue())) {
            addInvalidMessage(ctx, validationUnit.getName(), MessageFormat.format(errorMessage, new Object[]{digitsCount}));
        }
    }

    private boolean isValidDigit(String digit) {
        return (!NumberUtils.isDigits(digit) || (digit.length() > digitsCount));
    }

}
