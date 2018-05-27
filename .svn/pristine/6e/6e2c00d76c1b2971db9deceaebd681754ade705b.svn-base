package cn.mldn.service.impl;

import cn.mldn.dao.IDeptDAO;
import cn.mldn.service.IDeptService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Dept;
@Service
public class DeptServiceImpl extends AbstractService implements IDeptService {
	@Autowired
	private IDeptDAO deptDAO ;
	@Override
	public boolean addDept(Dept vo) throws Exception {
		if (this.deptDAO.findByDname(vo.getDname()) == null) {
			return this.deptDAO.doCreate(vo);
		}
		return false;
	}

}
