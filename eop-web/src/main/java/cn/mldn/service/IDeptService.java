package cn.mldn.service;

import cn.mldn.vo.Dept;

public interface IDeptService {
	/**
	 * 实现部门数据的增加
	 * @param vo 数据增加
	 * @return 部门增加成功返回true，增加失败返回false
	 * @throws Exception 程序异常
	 */
	public boolean addDept (Dept vo) throws Exception ;
}
