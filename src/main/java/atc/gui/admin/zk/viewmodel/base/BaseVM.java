package atc.gui.admin.zk.viewmodel.base;

import atc.gui.admin.security.SpringSecurityHelper;
import atc.gui.admin.zk.transition.PathPart;
import atc.gui.admin.zk.transition.TransitionManager;
import atc.gui.admin.zk.viewmodel.export.DataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRParameter;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zkmax.zul.Filedownload;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public abstract class BaseVM
{
	@Getter
	@Setter
	@WireVariable("transitionManager")
	private TransitionManager transitionManager;

	protected String getParentAttribute(String name)
	{
		return transitionManager.getParentPathPart().getValue(name);
	}
	protected String getAttribute(String name)
	{
		return transitionManager.getParamValueInTop(name);
	}

	protected void navigateTo(PathPart pathPart)
	{
		transitionManager.forward(pathPart);
	}
	protected void navigateBack()
	{
		transitionManager.back();
	}
	
	public <T> void exportReport(List<T> data, Class<T> type)
	{
		DataSource<T> src = new DataSource<>(data);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableDataSource", src);
		params.put("creatorLogin", SpringSecurityHelper.getCurrentUser().getLogin());
		params.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

		String template = String.format("reports/%s.jasper", type.getSimpleName());
		
		log.info(String.format("report request for class %s, path %s", type.getSimpleName(), template));
		
		Jasperreport report = new Jasperreport();
		report.setType("xls");
		report.setSrc(template);
		report.setParameters(params);

		InputStream is = new ByteArrayInputStream(report.getReport().getByteData());
		Filedownload.save(is, "xls", "report.xls" );
	}
}
