package cn.mldn.service;

import cn.mldn.vo.Emp;

public interface ILoginServile {

    /**
     * 用户登录处理，根据用户名和密码进行登录操作
     * @param vo 包含有用户名和密码（加密）
     * @return 登录成功返回true，否则返回false
     * @throws Exception SQL
     */
    public boolean login(Emp vo) throws Exception ;
}
