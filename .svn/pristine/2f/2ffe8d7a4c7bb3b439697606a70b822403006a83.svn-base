package cn.mldn.action;

import cn.mldn.service.ISupplyPurchaseService;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.vo.Supply_purchase;

@Controller
@RequestMapping("/pages/back/admin/supply/*")
public class SupplyPurchaseAction extends AbstractAction {

	@Autowired
	private ISupplyPurchaseService supply_purchaseService;

	/**
	 * 进行采购表的增加
	 * 
	 * @param sup
	 * @throws Exception
	 */
	//获取申请雇员ID需要添加
	@RequestMapping("supply_apply_add")
	public ModelAndView add(String title, String note) throws Exception {
		Supply_purchase sup = new Supply_purchase();
		sup.setTitle(title);
		sup.setNote(note);
		supply_purchaseService.add(sup);
		ModelAndView mav = new ModelAndView(super.getPage("supply_goods_add.page"));
		return mav;
	}
}
