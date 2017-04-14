package atc.gui.admin.domain;

import atc.gui.admin.zk.UIType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface FormField 
{
    long flags() default 0;
    
    UIType type() default UIType.TEXTFIELD;
}