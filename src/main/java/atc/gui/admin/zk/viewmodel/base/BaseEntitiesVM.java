package atc.gui.admin.zk.viewmodel.base;

import atc.gui.admin.zk.transition.PathPart;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Messagebox;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
public abstract class BaseEntitiesVM<T> extends BaseVM
{
	@Getter
	private List<T> dataList = new ArrayList<T>();
	
	@Getter @Setter
	private T selected;

	@Init
	public void baseEntitiesInit()
	{
		initDataList(dataList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command("delete")
	public void delete()
	{
        Messagebox.show(Labels.getLabel("view.dialog.delete.entity.text"),
                        Labels.getLabel("view.dialog.delete.entity.title"),
                Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                event -> {
                    if (event.getName().equals("onOK"))
                    {
                        remove(selected);
                        dataList.remove(selected);
                        BindUtils.postNotifyChange(null, null, BaseEntitiesVM.this, "dataList");
                    }
                });
	}
	
	@Command("create")
	public void create()
	{
		try 
		{
			openEditView(getEntityClass().newInstance());
		}
		catch (Exception e)
		{
			log.error("can't create new instance of " + getEntityClass(), e);
		}
	}
	
	@Command("edit")
	public void edit()
	{
		openEditView(selected);
	}
	
	private void openEditView(T entity)
	{
		PathPart editPathPart = getEditPathPart(entity);
		log.debug("open edit view path: " + editPathPart.asString());
		navigateTo(editPathPart);
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass()
	{
		return (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected abstract PathPart getEditPathPart(T entity);
	
	protected abstract void remove(T entity);

    protected abstract void initDataList(List<T> dataList);

}
