package atc.gui.admin.zk.viewmodel.export;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRParameter;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zkmax.zul.Filedownload;

public class ExportComponentVM<T>
{
	private static final String COMPILED_REPORTS_PATH_TEMPLATE = "WEB-INF/reports/%s.jasper";
	private static final String USER_DATA_SOURCE_REF = "userDataSource";
	private static final Locale RU_LOCALE = new Locale("ru", "RU");
	
	@Getter
	private String[] exportTypes = new String[] {"csv", "xls", "pdf"};
	
	@Getter @Setter
	private String selectedType = exportTypes[0];
	
	private DataSourceFactory<T> factory;
	
    @Init
    public void init(@BindingParam("dataSourceFactory") DataSourceFactory<T> factory)
    {
    	this.factory = factory;
    }
 
	@Command("doExport")
	public void doExport()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(USER_DATA_SOURCE_REF, factory.createDataSource());
		params.put(JRParameter.REPORT_LOCALE, RU_LOCALE);
		
		Jasperreport report = new Jasperreport();
		report.setType(selectedType);
		report.setSrc(getTemplatePath(factory.getType()));
		report.setParameters(params);
		InputStream is = new ByteArrayInputStream(report.getReport().getByteData());
		Filedownload.save(is, selectedType, "report." + selectedType);
	}

	private String getTemplatePath(Class<T> entityClass)
	{
		String subPath = String.format(COMPILED_REPORTS_PATH_TEMPLATE, entityClass.getSimpleName());
		return Executions.getCurrent().getDesktop().getWebApp().getServletContext().getRealPath(subPath);
	}

}
