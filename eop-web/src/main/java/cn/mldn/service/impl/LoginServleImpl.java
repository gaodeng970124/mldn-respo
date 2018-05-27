package cn.mldn.service.impl;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.service.ILoginServile;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Emp;

@Service
public class LoginServleImpl extends AbstractService implements ILoginServile {

    @Autowired
    private IEmpDAO empDAO;

    @Override
    public boolean login(Emp vo) throws Exception {
        Emp emp = empDAO.findById(vo.getEid());
        if (emp != null) {
            return emp.getPassword().equals(vo.getPassword());
        }
        return false;
    }
}
