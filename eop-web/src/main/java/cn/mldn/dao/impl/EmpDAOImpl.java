package cn.mldn.dao.impl;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Emp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class EmpDAOImpl extends AbstractDAO implements IEmpDAO {

    @Override
    public boolean doCreate(Emp vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Emp vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<String> ids) throws SQLException {
        return false;
    }

    @Override
    public Emp findById(String id) throws SQLException {
        String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status from emp where eid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Emp vo = new Emp();
            vo.setEid(rs.getString(1));
            vo.setLid(rs.getInt(2));
            vo.setDid(rs.getLong(3));
            vo.setEname(rs.getString(4));
            vo.setSalary(rs.getDouble(5));
            vo.setPhone(rs.getString(6));
            vo.setPassword(rs.getString(7));
            vo.setPhoto(rs.getString(8));
            vo.setNote(rs.getString(9));
            vo.setHiredate(rs.getDate(10));
            vo.setIneid(rs.getString(11));
            vo.setStatus(rs.getInt(12));
            return vo;
        }
        return null;
    }

    @Override
    public List<Emp> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Emp> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Emp> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }

    @Override
    public List<Emp> getByEidList(List<String> eids) throws SQLException {
        StringBuffer sql = new StringBuffer();
        ArrayList<Emp> list = new ArrayList<>();
        sql.append("select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status from emp where eid in( ");
        eids.forEach((eid) -> sql.append("'").append(eid).append("'").append(","));
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Emp vo = new Emp();
            vo.setEid(rs.getString(1));
            vo.setLid(rs.getInt(2));
            vo.setDid(rs.getLong(3));
            vo.setEname(rs.getString(4));
            vo.setSalary(rs.getDouble(5));
            vo.setPhone(rs.getString(6));
            vo.setPassword(rs.getString(7));
            vo.setPhoto(rs.getString(8));
            vo.setNote(rs.getString(9));
            vo.setHiredate(rs.getDate(10));
            vo.setIneid(rs.getString(11));
            vo.setStatus(rs.getInt(12));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Emp> getBylEidList(String eid) throws SQLException {
        String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status " +
                " from emp where lid > (SELECT lid FROM emp where eid = ?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        ResultSet rs = super.pstmt.executeQuery();
        ArrayList<Emp> list = new ArrayList<>();
        while (rs.next()) {
            Emp vo = new Emp();
            vo.setEid(rs.getString(1));
            vo.setLid(rs.getInt(2));
            vo.setDid(rs.getLong(3));
            vo.setEname(rs.getString(4));
            vo.setSalary(rs.getDouble(5));
            vo.setPhone(rs.getString(6));
            vo.setPassword(rs.getString(7));
            vo.setPhoto(rs.getString(8));
            vo.setNote(rs.getString(9));
            vo.setHiredate(rs.getDate(10));
            vo.setIneid(rs.getString(11));
            vo.setStatus(rs.getInt(12));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Emp> getDidEmpList(String eid, Long did) throws SQLException {
        String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status " +
                " from emp where lid > (SELECT lid FROM emp where eid = ?) and did = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        super.pstmt.setLong(2, did);
        ResultSet rs = super.pstmt.executeQuery();
        ArrayList<Emp> list = new ArrayList<>();
        while (rs.next()) {
            Emp vo = new Emp();
            vo.setEid(rs.getString(1));
            vo.setLid(rs.getInt(2));
            vo.setDid(rs.getLong(3));
            vo.setEname(rs.getString(4));
            vo.setSalary(rs.getDouble(5));
            vo.setPhone(rs.getString(6));
            vo.setPassword(rs.getString(7));
            vo.setPhoto(rs.getString(8));
            vo.setNote(rs.getString(9));
            vo.setHiredate(rs.getDate(10));
            vo.setIneid(rs.getString(11));
            vo.setStatus(rs.getInt(12));
            list.add(vo);
        }
        return list;
    }

    @Override
    public boolean updatePassword(String password, String eid) throws SQLException {
        String sql = "update emp set password = ? where eid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, password);
        super.pstmt.setString(2, eid);
        return super.pstmt.executeUpdate() > 0;
    }
}
