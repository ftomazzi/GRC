package com.idsscheer.webapps.arcm.dl.framework.viewhandler;

import java.util.List;

import com.idsscheer.webapps.arcm.dl.framework.BusViewException;
import com.idsscheer.webapps.arcm.dl.framework.IDataLayerObject;
import com.idsscheer.webapps.arcm.dl.framework.IFilterCriteria;
import com.idsscheer.webapps.arcm.dl.framework.IRightsFilterCriteria;
import com.idsscheer.webapps.arcm.dl.framework.dllogic.QueryDefinition;

public class CustomControlViewHandler implements IViewHandler {

	public CustomControlViewHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleView(QueryDefinition query, List<IRightsFilterCriteria> rightsFilters,
			List<IFilterCriteria> filters, IDataLayerObject currentObject, QueryDefinition parentQuery) throws BusViewException {
		// TODO Auto-generated method stub

	}

}
