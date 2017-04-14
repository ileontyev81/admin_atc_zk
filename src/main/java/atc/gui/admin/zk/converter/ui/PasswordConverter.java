package atc.gui.admin.zk.converter.ui;

import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zul.Textbox;

public class PasswordConverter implements Converter<String, String, Textbox>
{
	private String savedPassword = null;
	
	@Override
	public String coerceToUi(String beanProp, Textbox component, BindContext ctx)
	{
		this.savedPassword = beanProp;
		return beanProp;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String coerceToBean(String compAttr, Textbox component, BindContext ctx)
	{
		if (!savedPassword.equals(compAttr))
		{
			return ((BasePasswordEncoder)ctx.getConverterArg("encoder")).encodePassword(compAttr, null);
		}
		return savedPassword;
	}

}
