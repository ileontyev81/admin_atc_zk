package atc.gui.admin.zk.validator;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.zk.validator.form.ValidationUnit;
import atc.gui.admin.zk.validator.form.factory.ValidationUnitFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Form;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.proxy.FormProxyHandler;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Row;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class Jsr303Validator<T> extends AbstractValidator
{
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Class<T> entryType;

    public Jsr303Validator(Class<T> entryType)
    {
    	this.entryType = entryType;
    }

    /**
     * Attention: UserEntry call validate for every property, other entries for all properties one time.
     */
    public void validate(ValidationContext ctx)
    {
        Map<String, Property> properties = ctx.getProperties(ctx.getProperty().getValue());
        if (properties.isEmpty() && ctx.getProperty().getProperty().equals(".")) {
            BindContext bindContext = ctx.getBindContext();
            Component component = bindContext.getComponent();
            if (component instanceof Row) {
                Field field = ((Row) component).getValue();
                Object fieldValue = getInnerPropertyValues(ctx).get(field.getName());
                ValidationUnit validationUnit = ValidationUnitFactory.construct(field.getName(), fieldValue);
                validateProperty(validationUnit, ctx);
            }
        } else {
            for (Map.Entry<String, Property> entry : properties.entrySet()) {
                ValidationUnit validationUnit = ValidationUnitFactory.construct(entry.getKey(), entry.getValue().getValue());
                if (!validationUnit.isUseless()) {
                    validateProperty(validationUnit, ctx);
                }
            }
        }
    }

    private void validateProperty(ValidationUnit validationUnit, ValidationContext ctx) {
        Set<ConstraintViolation<T>> violationSet = validator.validateValue(entryType, validationUnit.getName(), validationUnit.getValue());
        if (!violationSet.isEmpty())
        {
            addInvalidMessage(ctx, validationUnit.getName(), getErrorMessage(violationSet));
        }
    }

    /**
     * Attention!Hack!
     * There is no all properties on some entries(UserEntry) got by standart codeflow
     */
    private Map<String, Object> getInnerPropertyValues(ValidationContext ctx) {
        Map<String, Object> cache = new HashMap<>();
        try {
            Form form = (Form) ctx.getProperty().getValue();
            Object origin = form.getFormStatus().getOrigin();
            Field handlerField = origin.getClass().getDeclaredField("handler");
            handlerField.setAccessible(true);
            FormProxyHandler proxyHandler = (FormProxyHandler)handlerField.get(origin);
            Field cacheField = ReflectionUtils.findField(proxyHandler.getClass(), "_cache");
            cacheField.setAccessible(true);
            cache.putAll((Map<String, Object>)cacheField.get(proxyHandler));
        } catch (Exception exception) {
            log.error("", exception);
        }
        return filterFormFieldProperties(cache);
    }

    private Map<String, Object> filterFormFieldProperties(Map<String, Object> properties) {
        Predicate<Map.Entry<String, Object>> filter = (mapEntry -> {
            String propName = mapEntry.getKey();
            Field propField = ReflectionUtils.findField(entryType, propName);
            propField.setAccessible(true);
            return propField.isAnnotationPresent(FormField.class);
        });
        return properties.entrySet().stream().filter(filter).collect(Collectors.toMap(mapEntry -> mapEntry.getKey(), mapEntry -> mapEntry.getValue()));
    }

    /**
     * Get first error message
     */
    public String getErrorMessage(Set<ConstraintViolation<T>> violationSet)
    {
        return violationSet.isEmpty() ? "" : violationSet.iterator().next().getMessage();
    }

}
