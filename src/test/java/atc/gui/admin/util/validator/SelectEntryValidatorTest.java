//package atc.gui.admin.util.validator;
//
//import java.util.Set;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import atc.gui.admin.model.AccountEntry;
//
//public class SelectEntryValidatorTest
//{
//	private static Validator validator;
//
//    @BeforeClass
//    public static void setUp() 
//    {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    public void testAccountEntryNoSelection() 
//    {
//    	AccountEntry accountEntry = new AccountEntry();
//        Set<ConstraintViolation<AccountEntry>> constraintViolations = validator.validate(accountEntry);
//        Assert.assertEquals(1, constraintViolations.size());
//        Assert.assertEquals("validator.selectentry.mustbeselected", constraintViolations.iterator().next().getMessageTemplate());
//    }
//
//    @Test
//    public void testAccountEntrySelectIsValid() 
//    {
//    	AccountEntry accountEntry = new AccountEntry();
//        Set<ConstraintViolation<AccountEntry>> constraintViolations = validator.validate(accountEntry);
//        Assert.assertEquals(0, constraintViolations.size());
//    }
//	
//}
