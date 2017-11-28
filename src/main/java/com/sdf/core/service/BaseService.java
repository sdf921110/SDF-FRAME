package com.sdf.core.service;

import java.math.BigDecimal;

import com.sdf.common.exception.CustomRuntimeException;
import com.sdf.common.pojo.BaseEntity;
import com.sdf.common.pojo.SessionUser;
import com.sdf.common.utils.DateUtil;
import com.sdf.core.controller.BaseController;
import com.sdf.core.pojo.system.SysUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class BaseService {
    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取session信息
     *
     * @return Object
     * @time 2016年11月22日 上午10:00:59
     */
    protected Object getSesseion() {
        if (session.getAttribute(BaseController.BACK_SESSION_USER) == null) {
            return null;
        }
        return session.getAttribute(BaseController.BACK_SESSION_USER);
    }

    /**
     * 获取session信息中登录对象
     *
     * @return SessionUser
     * @time 2016年11月22日 上午10:01:31
     */
    protected SessionUser getSesseionUser() {
        if (session.getAttribute(BaseController.BACK_SESSION_USER) == null) {
            return null;
        }
        return (SessionUser) session.getAttribute(BaseController.BACK_SESSION_USER);
    }

    /**
     * 获取操作人对象
     *
     * @return
     * @time 2016年11月22日 上午10:00:59
     */
    protected SysUser getSysUser() {
        if (session.getAttribute(BaseController.BACK_SESSION_USER) == null) {
            return null;
        }
        SessionUser SessionUser = (SessionUser) session.getAttribute(BaseController.BACK_SESSION_USER);
        return SessionUser.getSysUser();
    }

    /**
     * 获取操作人
     *
     * @return
     * @time 2016年11月22日 上午10:00:59
     */
    protected String getSesseionUserName() {
        SysUser sysUser = getSysUser();
        return sysUser.getName();
    }

    /**
     * 获取操作人ID
     *
     * @return
     * @time 2017年3月24日 下午3:33:10
     */
    protected Integer getSesseionUserId() {
        SysUser sysUser = getSysUser();
        return sysUser.getId();
    }

    /**
     * 新增 增加用户信息
     *
     * @param baseEntity
     */
    protected void insertCreate(BaseEntity baseEntity) {
        try {
            SysUser sysUser = getSysUser();
            baseEntity.setCreate_id(sysUser.getId());
            baseEntity.setCreate_user(sysUser.getName());
            baseEntity.setCreate_time(DateUtil.getCurrentDate());
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
            logger.error("创建修改基本信息初始化失败！" + e.getMessage(), e);
        }
    }

    /**
     * 修改 增加用户信息
     *
     * @param baseEntity
     */
    protected void updateCreate(BaseEntity baseEntity) {
        try {
            SysUser sysUser = getSysUser();
            baseEntity.setUpdate_id(sysUser.getId());
            baseEntity.setUpdate_user(sysUser.getName());
            baseEntity.setUpdate_time(DateUtil.getCurrentDate());
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
            logger.error("创建修改基本信息初始化失败！" + e.getMessage(), e);
        }
    }

    @SuppressWarnings("static-access")
    protected BigDecimal getBigDecimal(Object bd) {
        if (bd == null) {
            return new BigDecimal(0);
        }
        if (bd instanceof BigDecimal) {
            BigDecimal b = (BigDecimal) bd;
            return b.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        try {
            return new BigDecimal(bd + "");
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

}
