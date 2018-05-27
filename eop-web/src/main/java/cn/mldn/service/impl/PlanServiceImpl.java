package cn.mldn.service.impl;

import cn.mldn.dao.*;
import cn.mldn.service.IPlanService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PlanServiceImpl extends AbstractService implements IPlanService {

    @Autowired
    IEmpDAO empDAO;

    @Autowired
    IDeptDAO deptDAO;

    @Autowired
    IPlanDAO planDAO;

    @Autowired
    ILevelDAO levelDAO;

    @Autowired
    IDictionaryDAO dictionaryDAO;

    @Autowired
    IPlanDetailsDAO planDetailsDAO;

    @Override
    public List<Long> deleteUnpublishedPlan(Set<Long> ids) throws Exception {
        List<Long> list = planDAO.findByIdAndStatusList(ids);
        if (list.size() == ids.size()) {
            planDAO.doRemove(ids);
        }
        return list;
    }

    @Override
    public Map<String, Object> getDictionaryType(String type) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("planList", dictionaryDAO.findByType(type));
        return map;
    }

    @Override
    public boolean addPlan(Plan vo) throws Exception {
        if (vo == null) {
            return false;
        }
        return planDAO.doCreate(vo);
    }

    @Override
    public List<Long> getRemovePid(Set<Long> ids) throws Exception {
        if (ids == null) {
            return null;
        }
        return planDAO.findByIdAndStatusList(ids);
    }

    @Override
    public Map<String, Object> UnpublishedPlanList(Long currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.isSearch(column, keyWord)) {
            map.put("planList", this.planDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allRecorders", this.planDAO.getAllCount(column, keyWord));
        } else {
            map.put("planList", this.planDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", this.planDAO.getAllCount());
        }
        return map;
    }

    @Override
    public boolean updateStatus(long id) throws Exception {
        if (id == 0) {
            return false;
        }
        return planDAO.updateStatus(id);
    }

    @Override
    public Map<String, Object> getPlanDetails(long pid) throws Exception {
        if (pid == 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        List<String> pidList = planDetailsDAO.findByPid(pid);
        if (pidList.size() != 0) {
            List<Emp> empList = empDAO.getByEidList(pidList);
            map.put("levelList", levelDAO.findAll());
            map.put("empList", empList);
        }
        map.put("deptList", deptDAO.findAll());
        map.put("plan", planDAO.findById(pid));
        return map;
    }

    @Override
    public Map<String, Integer> deletePlanPersonnel(String eid, Long pid) throws Exception {
        if (eid == null || "".equals(eid)) {
            return null;
        }
        HashMap<String, Integer> map = new HashMap<>();
        if (planDetailsDAO.getEidRemove(eid)) {
            planDAO.updateAmount(-1L, pid);
            map.put("amount", planDAO.findByAmount(pid));
        }
        return map;
    }

    @Override
    public Map<String, Object> planDetailsModal(String eid, Long pid) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pidList", planDetailsDAO.existsEid(pid));
        map.put("levelList", levelDAO.findAll());
        map.put("deptList", deptDAO.findAll());
        map.put("empList", empDAO.getBylEidList(eid));
        return map;
    }

    @Override
    public Map<String, Object> getDidEmpList(String eid, Long did, Long pid) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("levelList", levelDAO.findAll());
        map.put("pidList", planDetailsDAO.existsEid(pid));
        if (did == 9999) {
            map.put("empList", empDAO.getBylEidList(eid));
        } else {
            map.put("empList", empDAO.getDidEmpList(eid, did));
        }
        return map;
    }

    @Override
    public Map<String, Object> addDetails(String eid, Long pid) throws Exception {
        boolean flag = false;
        HashMap<String, Object> map = new HashMap<>();
        if (planDetailsDAO.currentPidExists(eid, pid)) {
            if (planDetailsDAO.findByEidAndPid(eid, pid) == 0L) {
                PlanDetails vo = new PlanDetails();
                vo.setEid(eid);
                vo.setPid(pid);
                Emp emp = empDAO.findById(eid);
                flag = planDetailsDAO.doCreate(vo);
                planDAO.updateAmount(1L, pid);
                Plan plan = planDAO.findById(pid);
                map.put("emp", emp);
                map.put("amount", plan.getAmount());
                map.put("pidList", planDetailsDAO.existsEid(pid));
                map.put("deptList", deptDAO.findAll());
                map.put("levelList", levelDAO.findAll());
            }
        }
        map.put("flag", flag);
        return map;
    }
}
