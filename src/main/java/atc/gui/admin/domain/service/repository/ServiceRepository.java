package atc.gui.admin.domain.service.repository;

import java.util.List;

import atc.gui.admin.domain.model.ServiceEntry;

public interface ServiceRepository
{
	public void saveService(ServiceEntry e);
	public ServiceEntry getService(int id);
	public List<ServiceEntry> getServiceList();
	public void deleteService(int id);
}
