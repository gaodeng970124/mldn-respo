package cn.mldn.service.impl;

import cn.mldn.dao.ISupplyPurchaseDAO;
import cn.mldn.service.ISupplyPurchaseService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Supply_purchase;
@Service
public class SupplyPurchaseServiceImpl extends AbstractService implements ISupplyPurchaseService {
	@Autowired
	private ISupplyPurchaseDAO supply_purchaseDAO ;
	@Override
	public boolean add(Supply_purchase sup) throws Exception {
		return this.supply_purchaseDAO.doCreate(sup) ;
	}

}
