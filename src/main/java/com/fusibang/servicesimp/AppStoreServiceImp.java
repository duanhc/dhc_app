//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fusibang.dao.AppStoreDao;
import com.fusibang.help.AppStoreHelp;
import com.fusibang.help.Config;
import com.fusibang.help.QRCodeUtil;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.AppStoreService;
import com.fusibang.tables.AppStore;

public class AppStoreServiceImp extends ResponseStatus implements AppStoreService {
    private AppStoreDao appStoreDao;
    private Config config;
    private AppStoreHelp appStoreHelp;

    public AppStoreServiceImp() {
    }

    public String add(AppStore app, HttpSession session) {
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else if(!this.appStoreDao.existName(app.getName())) {
                this.appStoreDao.add(app);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"already_exist\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String alt(AppStore app, HttpSession session) {
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                AppStore hold = this.appStoreDao.findById(app.getId());
                if(!app.getName().equals(hold.getName()) && this.appStoreDao.existName(app.getName())) {
                    return "{\"hint\":\"already_exist\"}";
                } else {
                    app.setAdd_time(hold.getAdd_time());
                    app.setAll_ua(hold.getAll_ua());
                    app.setPriority(hold.getPriority());
                    app.setPut_away(hold.getPut_away());
                    app.setToday_ua(hold.getToday_ua());
                    app.setYester_ua(hold.getYester_ua());
                    this.appStoreDao.getSession().merge(app);
                    return "{\"hint\":\"success\"}";
                }
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String put(AppStore app, HttpSession session) {
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                this.appStoreDao.putAway(app);
                return "{\"hint\":\"success\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String priority(AppStore app, HttpSession session) {
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                this.appStoreDao.priority(app);
                return "{\"hint\":\"success\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String delete(int id, HttpSession session) {
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                this.appStoreDao.delete(id);
                return "{\"hint\":\"success\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String getUrl(int id, HttpSession session) {
        AppStore app = this.appStoreDao.findById(id);
        String url = "http://" + this.config.getProjectHost() + "/" + this.config.getProjectName() + "/app_market.do";
        if(app != null && app.getPut_away().intValue() == 1) {
            url = app.getApp_url();
            if(session.getAttribute("am" + id) == null) {
                app.setToday_ua(app.getToday_ua() + 1);
                app.setAll_ua(app.getAll_ua() + 1);
                session.setAttribute("am" + id, "");
            }
        }

        return url;
    }

    public String altView(int id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                AppStore app = this.appStoreDao.findById(id);
                if(app != null && app.getPut_away().intValue() == 0) {
                    request.setAttribute("app", this.appStoreDao.findById(id));
                    return "success";
                } else {
                    return "illegal_request";
                }
            }
        } else {
            return "un_login";
        }
    }

    public String putView(AppStore app, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                int count = this.appStoreDao.getCount(app.getPut_away().intValue(), app.getName());
                int pageCount = (int)Math.ceil((double)count / 18.0D);
                if(app.getId() > pageCount && count != 0) {
                    return "illegal_request";
                } else {
                    request.setAttribute("apps", this.appStoreDao.getAppsByPage(app.getId(), app.getPut_away().intValue(), app.getName()));
                    request.setAttribute("pageCount", Integer.valueOf(pageCount));
                    request.setAttribute("count", Integer.valueOf(count));
                    request.setAttribute("thisPage", Integer.valueOf(app.getId()));
                    request.setAttribute("name", app.getName());
                    request.setAttribute("put", app.getPut_away());
                    return "success";
                }
            }
        } else {
            return "un_login";
        }
    }

    public String downView(AppStore app, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                int count = this.appStoreDao.getCount(app.getPut_away().intValue(), app.getName());
                int pageCount = (int)Math.ceil((double)count / 18.0D);
                if(app.getId() > pageCount && count != 0) {
                    return "illegal_request";
                } else {
                    request.setAttribute("apps", this.appStoreDao.getAppsByPage(app.getId(), app.getPut_away().intValue(), app.getName()));
                    request.setAttribute("pageCount", Integer.valueOf(pageCount));
                    request.setAttribute("count", Integer.valueOf(count));
                    request.setAttribute("thisPage", Integer.valueOf(app.getId()));
                    request.setAttribute("name", app.getName());
                    request.setAttribute("put", app.getPut_away());
                    return "success";
                }
            }
        } else {
            return "un_login";
        }
    }

    public String marketView(HttpServletRequest request) {
        request.setAttribute("asorder", this.appStoreHelp.getAppStoresAsOrder());
        request.setAttribute("astime", this.appStoreHelp.getAppStoresAsTime());
        request.setAttribute("asua", this.appStoreHelp.getAppStoresAsUa());
        return "success";
    }

    public String jieguotView(HttpServletRequest request) {
        request.setAttribute("hidden", this.appStoreHelp.getAppStoresHidden());
        return "success";
    }

    public String getQRCode(int id, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                AppStore app = this.appStoreDao.findById(id);
                String url = "http://" + this.config.getProjectHost() + "/" + this.config.getProjectName() + "/app_redirect.do?id=" + id;
                String path = request.getRealPath("/") + "upload";
                ServletOutputStream out = null;

                try {
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    String e = new String((app.getName() + ".png").getBytes(), "ISO-8859-1");
                    response.setHeader("Content-Disposition", "attachment;filename=" + e);
                    response.addHeader("Pargam", "no-cache");
                    response.addHeader("Cache-Control", "no-cache");
                    out = response.getOutputStream();
                    QRCodeUtil.encode(url, path + "\\" + app.getLogo(), out, true);
                } catch (Exception var11) {
                    var11.printStackTrace();
                }

                return null;
            }
        } else {
            return "un_login";
        }
    }

    public void setAppStoreDao(AppStoreDao appStoreDao) {
        this.appStoreDao = appStoreDao;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public void setAppStoreHelp(AppStoreHelp appStoreHelp) {
        this.appStoreHelp = appStoreHelp;
    }
}
