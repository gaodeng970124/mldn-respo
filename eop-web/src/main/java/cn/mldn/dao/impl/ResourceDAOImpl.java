package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.IResourceDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Resource;

@Repository
public class ResourceDAOImpl extends AbstractDAO implements IResourceDAO {

    @Override
    public boolean doCreate(Resource vo) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean doEdit(Resource vo) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Resource findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Resource> findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Resource> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        List<Resource> all = new ArrayList<Resource>();
        String sql = "SELECT resid,spid,title,price,appdate,amount,photo,status,item,note FROM resource WHERE status=1 LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, (currentPage - 1) * lineSize);
        super.pstmt.setInt(2, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Resource vo = new Resource();
            vo.setResid(rs.getLong(1));
            vo.setSpid(rs.getLong(2));
            vo.setTitle(rs.getString(3));
            vo.setPrice(rs.getDouble(4));
            vo.setAppdate(rs.getDate(5));
            vo.setAmount(rs.getInt(6));
            vo.setPhoto(rs.getString(7));
            vo.setStatus(rs.getInt(8));
            vo.setItem(rs.getString(9));
            vo.setNote(rs.getString(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Resource> findSplit(Long currentPage, Integer lineSize, String column, String keyWord)
            throws SQLException {
        List<Resource> all = new ArrayList<Resource>();
        String sql = "SELECT resid,spid,title,price,appdate,amount,photo,status,item,note FROM " +
                " resource WHERE status=1 AND "
                + column + " LIKE ? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setLong(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Resource vo = new Resource();
            vo.setResid(rs.getLong(1));
            vo.setSpid(rs.getLong(2));
            vo.setTitle(rs.getString(3));
            vo.setPrice(rs.getDouble(4));
            vo.setAppdate(rs.getDate(5));
            vo.setAmount(rs.getInt(6));
            vo.setPhoto(rs.getString(7));
            vo.setStatus(rs.getInt(8));
            vo.setItem(rs.getString(9));
            vo.setNote(rs.getString(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return super.getAllCountHandle("resource"); // 根据AbstractDAO中的方法，计算“resource”数据表中的个数统计
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        return super.getAllCountSplitHandle("resource", column, keyWord);// 根据AbstractDAO中的方法，计算“resource”数据表中的模糊查询后的个数统计
    }

}
