package atc.gui.admin.zk.viewmodel;

import atc.gui.admin.domain.model.ServiceEntry;
import atc.gui.admin.domain.service.repository.ServiceRepository;
import atc.gui.admin.zk.viewmodel.base.BaseEntityVM;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.spring.DelegatingVariableResolver.class)
@Init(superclass = true)
public class ServiceVM extends BaseEntityVM<ServiceEntry>
{
	private ServiceRepository repository;

	@WireVariable("serviceRepository")
	public void setRepository(ServiceRepository repository)
	{
		this.repository = repository;
	}
	
	@Override
	protected ServiceEntry loadEntity(String entityId)
	{
		return repository.getService(Integer.parseInt(entityId));
	}

	@Override
	protected void saveEntity(ServiceEntry entity)
	{
		repository.saveService(entity);
	}
}
