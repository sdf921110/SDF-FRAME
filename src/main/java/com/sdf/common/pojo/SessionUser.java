package com.sdf.common.pojo;

import com.sdf.core.pojo.system.SysUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录用户对象
 *
 * @author SDF
 * @date 2016年11月9日
 */
public class SessionUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 系统名称
     */
    public static enum SystemName {
        Frame
    }

    /**
     * 权限类型
     */
    public static enum RightsType {
        管理员, 普通用户
    }

    /**
     * 权限状态
     */
    public static enum UserStatus {
        正常, 冻结, 注销
    }

    /*** 系统名称 */
    private String systemName = SystemName.Frame.name();
    /*** 权限类型 */
    private String rightsName = RightsType.管理员.name();
    /*** 权限状态 */
    private String statusName = UserStatus.正常.name();

    /*** 用戶名(登录名) */
    private String user_name;
    /*** 登录密码 */
    private String password;
    /*** 用户ID */
    private Integer user_id;
    /*** 用户姓名 */
    private String name;

    /*** 用户对象 */
    private SysUser sysUser;

    // /*** 门店对象 */
    // private StorePO storePO;
    // /*** 门店员工对象 */
    // private StoreUserPO storeUserPO;

    /*** 当前菜单编号 */
    private String currenMenuCode;
    /*** 一级菜单编号 */
    private String fstMenuCode;
    /*** 二级菜单编号 */
    private String scdMenuCode;
    /*** 三级菜单编号 */
    private String thdMenuCode;
    /*** 一级功能模块菜单 */
    private List<Map<String, Object>> fmenus = new ArrayList<>();
    /*** 二级功能模块菜单 */
    private Map<String, List<Map<String, Object>>> scdMenuMap = new HashMap<>();
    /*** 三级功能模块菜单 */
    private Map<String, List<Map<String, Object>>> thdMenuMap = new HashMap<>();

    public SessionUser() {

    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getRightsName() {
        return rightsName;
    }

    public void setRightsName(String rightsName) {
        this.rightsName = rightsName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCurrenMenuCode() {
        return currenMenuCode;
    }

    public String getFstMenuCode() {
        return fstMenuCode;
    }

    public String getScdMenuCode() {
        return scdMenuCode;
    }

    public String getThdMenuCode() {
        return thdMenuCode;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 获取一级功能菜单列表
     *
     * @return
     */
    public List<Map<String, Object>> getFmenus() {
        return fmenus;
    }

    /**
     * 获取二级功能菜单列表
     *
     * @return
     */
    public Map<String, List<Map<String, Object>>> getScdMenuMap() {
        return scdMenuMap;
    }

    /**
     * 获取三级功能菜单列表
     *
     * @return
     */
    public Map<String, List<Map<String, Object>>> getThdMenuMap() {
        return thdMenuMap;
    }

    /**
     * 设置用户的功能菜单
     *
     * @param menus
     */
    public void setMoudlesMunes(List<Map<String, Object>> menus) {
        for (Map<String, Object> map : menus) {
            String level = map.get("level") + "";
            String menuName = map.get("menuName") + "";
            String menuCode = map.get("menuCode") + "";
            String menuUrl = map.get("menuUrl") + "";
            String[] codes = menuCode.split("_");
            // 如果是按钮，则不加"menuCode"
            if (menuUrl.indexOf("/") != -1) {
                map.put("menuUrl", menuUrl + "menuName=" + menuName + "&menuCode=" + menuCode);
            } else {
                map.put("menuUrl", menuUrl);
            }
            if (level.equals("0")) {
                fmenus.add(map);
            } else if (level.equals("1")) {
                String pCode = codes[0];
                List<Map<String, Object>> level1List = scdMenuMap.get(pCode);
                if (level1List == null) {
                    level1List = new ArrayList<>();
                    scdMenuMap.put(pCode, level1List);
                }
                scdMenuMap.get(pCode).add(map);
            } else if (level.equals("2")) {
                String pCode = codes[0] + "_" + codes[1];
                List<Map<String, Object>> level2List = thdMenuMap.get(pCode);
                if (level2List == null) {
                    level2List = new ArrayList<>();
                    thdMenuMap.put(pCode, level2List);
                }
                thdMenuMap.get(pCode).add(map);
            }
        }
    }

    public void setCurrenMenuCode(String currenMenuCode) {
        this.currenMenuCode = currenMenuCode;
        if (currenMenuCode != null && !currenMenuCode.equals("")) {
            String[] codes = currenMenuCode.split("_");
            switch (codes.length) {
                case 3:
                    this.fstMenuCode = codes[0];
                    this.scdMenuCode = codes[0] + "_" + codes[1];
                    this.thdMenuCode = currenMenuCode;
                    break;
                case 2:
                    this.fstMenuCode = codes[0];
                    this.scdMenuCode = currenMenuCode;
                    this.thdMenuCode = null;
                    break;
                default:
                    this.fstMenuCode = currenMenuCode;
                    this.scdMenuCode = null;
                    this.thdMenuCode = null;
                    break;
            }
        }
    }

    /**
     * 后台用户
     *
     * @param sysUser
     * @param menus
     * @return
     * @time 2016年11月13日 下午3:53:21
     */
    public static SessionUser createSessionUser(SysUser sysUser, List<Map<String, Object>> menus) {
        SessionUser su = new SessionUser();
        Integer id = sysUser.getId();
        su.setId(id);
        su.setUser_id(id);
        su.setName(sysUser.getName());
        su.setUser_name(sysUser.getCode());
        if (sysUser.getSysRole().getIs_admin() == 1) {
            su.setRightsName(RightsType.管理员.name());
        } else {
            su.setRightsName(RightsType.普通用户.name());
        }
        if (sysUser.getStatus() == 1) {
            su.setStatusName(UserStatus.正常.name());
        } else if (sysUser.getStatus() == 2) {
            su.setStatusName(UserStatus.冻结.name());
        } else {
            su.setStatusName(UserStatus.注销.name());
        }
        su.setSysUser(sysUser);
        su.setMoudlesMunes(menus);
        return su;
    }

    // /**
    // * 门店管理员
    // *
    // * @param storePO
    // * @param sysUserPO
    // * @param menus
    // * @return
    // * @time 2016年11月13日 下午3:53:30
    // */
    // public static SessionUser createSessionUser(StorePO storePO,
    // List<Map<String, Object>> menus) {
    // SessionUser su = new SessionUser();
    // Integer id = storePO.getId();
    // su.setId(id);
    // su.setSys_user_id(storePO.getSys_user_id());
    // su.setStoreId(id);
    // su.setName(storePO.getLeader());
    // su.setUserName(storePO.getEmail());
    // su.setRightsName(RightsType.门店管理员.name());
    // su.setStorePO(storePO);
    // su.setMoudlesMunes(menus);
    // return su;
    // }

    // /**
    // * 门店员工
    // *
    // * @param storeUserPO
    // * @param menus
    // * @return
    // * @time 2016年11月13日 下午4:02:33
    // */
    // public static SessionUser createSessionUser(StoreUserPO storeUserPO,
    // List<Map<String, Object>> menus) {
    // SessionUser su = new SessionUser();
    // su.setId(storeUserPO.getId());
    // su.setSys_user_id(storeUserPO.getSys_user_id());
    // su.setStoreId(storeUserPO.getStore_id());
    // su.setName(storeUserPO.getName());
    // su.setUserName(storeUserPO.getPhone());
    // su.setRightsName(RightsType.门店员工.name());
    // su.setStoreUserPO(storeUserPO);
    // su.setMoudlesMunes(menus);
    // return su;
    // }

}
