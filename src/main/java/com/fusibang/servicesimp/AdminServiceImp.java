//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.dao.AdminDao;
import com.fusibang.help.MD5;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.AdminService;
import com.fusibang.tables.Admin;

public class AdminServiceImp extends ResponseStatus implements AdminService {
    private AdminDao adminDao;

    public AdminServiceImp() {
    }

    public String loginAdmin(Admin admin, HttpSession session) {
        Admin hold = this.adminDao.findByName(admin.getName());
        if(hold != null) {
            if(hold.getPassword().equals((new MD5()).getMD5ofStr(admin.getPassword()))) {
                if(hold.getForbid() == 0) {
                    session.setAttribute("ai", Integer.valueOf(hold.getId()));
                    session.setAttribute("ap", hold.getPermission());
                    session.removeAttribute("i");
                    return hold.getPermission().equals("00000")?"{\"hint\":\"rt_view\"}":"{\"hint\":\"success\"}";
                } else {
                    return "{\"hint\":\"account_forbid\"}";
                }
            } else {
                session.removeAttribute("i");
                return "{\"hint\":\"password_error\"}";
            }
        } else {
            session.removeAttribute("i");
            return "{\"hint\":\"account_not_found\"}";
        }
    }

    public String addAdmin(Admin admin, HttpSession session) {
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId == null) {
            return "{\"hint\":\"un_login\"}";
        } else if(permission.equals("11111") || permission.equals("00001") && admin.getPermission().equals("00000")) {
            if(this.adminDao.findByName(admin.getName()) == null) {
                Admin creater = this.adminDao.findById(adminId.intValue());
                admin.setCreater(creater);
                this.adminDao.addAdmin(admin);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"already_exist\"}";
            }
        } else {
            return "{\"hint\":\"illegal_request\"}";
        }
    }

    public String forbidAdmin(Admin admin, HttpSession session) {
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId == null) {
            return "{\"hint\":\"un_login\"}";
        } else {
            Admin entity = this.adminDao.findById(admin.getId());
            if(entity == null || entity.getCreater().getId() != adminId.intValue() && !permission.equals("11111")) {
                return "{\"hint\":\"illegal_request\"}";
            } else {
                entity.setForbid(admin.getForbid());
                return "{\"hint\":\"success\"}";
            }
        }
    }

    public String altAdminWithCreater(Admin admin, HttpSession session) {
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId == null) {
            return "{\"hint\":\"un_login\"}";
        } else {
            Admin entity = this.adminDao.findById(admin.getId());
            if(entity == null || entity.getCreater().getId() != adminId.intValue() && !permission.equals("11111")) {
                return "{\"hint\":\"illegal_request\"}";
            } else if(!entity.getName().equals(admin.getName()) && this.adminDao.findByName(admin.getName()) != null) {
                return "{\"hint\":\"already_exist\"}";
            } else {
                entity.setName(admin.getName());
                if(!admin.getPassword().equals(entity.getPassword())) {
                    entity.setPassword((new MD5()).getMD5ofStr(admin.getPassword()));
                }

                entity.setReal_name(admin.getReal_name());
                return "{\"hint\":\"success\"}";
            }
        }
    }

    public String forbidView(Admin admin, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("00001") && !permission.equals("11111")) {
                return "not_permission";
            } else {
                int count = this.adminDao.getCountByAdminId(admin_id.intValue(), admin.getPermission(), admin.getReal_name(), admin.getForbid());
                List admins = this.adminDao.getAdminsByAdminId(admin_id.intValue(), admin.getId(), admin.getPermission(), admin.getReal_name(), admin.getForbid());
                int pageCount = (int)Math.ceil((double)count / 18.0D);
                request.setAttribute("admins", admins);
                request.setAttribute("count", Integer.valueOf(count));
                request.setAttribute("pageCount", Integer.valueOf(pageCount));
                request.setAttribute("thisPage", Integer.valueOf(admin.getId()));
                request.setAttribute("name", admin.getReal_name());
                return "success";
            }
        } else {
            return "un_login";
        }
    }

    public String allView(Admin admin, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(permission.equals("11111")) {
                int count = this.adminDao.getCountAll(admin.getReal_name(), admin.getForbid());
                List admins = this.adminDao.getAdminsByAll(admin.getId(), admin.getReal_name(), admin.getForbid());
                int pageCount = (int)Math.ceil((double)count / 18.0D);
                request.setAttribute("admins", admins);
                request.setAttribute("count", Integer.valueOf(count));
                request.setAttribute("pageCount", Integer.valueOf(pageCount));
                request.setAttribute("thisPage", Integer.valueOf(admin.getId()));
                request.setAttribute("name", admin.getReal_name());
                return "success";
            } else {
                return "not_permission";
            }
        } else {
            return "un_login";
        }
    }

    public String altView(int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("00001") && !permission.equals("11111")) {
                return "not_permission";
            } else {
                Admin hold = this.adminDao.findById(id);
                if(hold != null && hold.getCreater().getId() == admin_id.intValue()) {
                    request.setAttribute("admin", hold);
                    return "success";
                } else {
                    return "illegal_request";
                }
            }
        } else {
            return "un_login";
        }
    }

    public String manage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            int admin_id = ((Integer)session.getAttribute("ai")).intValue();
            Admin admin = this.adminDao.findById(admin_id);
            request.setAttribute("permission", permission);
            request.setAttribute("admin", admin);
            return "success";
        } else {
            return "un_login";
        }
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
}
