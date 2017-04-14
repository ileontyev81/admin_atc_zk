package atc.gui.admin.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = atc.gui.admin.zk.validator.SelectEntryValidator.class)
@Documented
public @interface MustBeSelected 
{

    String message() default "{validator.selectentry.mustbeselected}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}