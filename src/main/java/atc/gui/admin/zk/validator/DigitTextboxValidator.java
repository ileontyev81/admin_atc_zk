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
