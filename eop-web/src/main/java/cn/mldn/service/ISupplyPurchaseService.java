package cn.mldn.service;

import cn.mldn.vo.Supply_purchase;

public interface ISupplyPurchaseService {
	/**
	 * 实现采购单的数据增加处理
	 * 
	 * @param sup 要保存的采购单信息
	 * @return 保存成功返回true,否则返回false
	 * @throws Exception 程序异常
	 */
	public boolean add(Supply_purchase sup) throws Exception;
}
