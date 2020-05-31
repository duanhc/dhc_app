//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.fusibang.dao.AdminDao;
import com.fusibang.dao.ChannelDao;
import com.fusibang.help.Config;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.ChannelService;
import com.fusibang.tables.Admin;
import com.fusibang.tables.Channel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChannelServiceImp extends ResponseStatus implements ChannelService {
    private AdminDao adminDao;
    private ChannelDao channelDao;
    private Config config;

    public ChannelServiceImp() {
    }

    public String addChannel(Channel channel, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("00001") && !permission.equals("11111")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                Admin creater = this.adminDao.findById(admin_id.intValue());
                Admin viewer = this.adminDao.findById(channel.getViewer().getId());
                if(viewer != null && viewer.getCreater().getId() == creater.getId()) {
                    if(!this.channelDao.existName(channel.getName())) {
                        channel.setCreater(creater);
                        channel.setViewer(viewer);
                        channel.setAdd_time(new Date((new java.util.Date()).getTime()));
                        this.channelDao.addChannel(channel);
                        return "{\"hint\":\"success\"}";
                    } else {
                        return "{\"hint\":\"already_exist\"}";
                    }
                } else {
                    return "{\"hint\":\"illegal_request\"}";
                }
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String addView(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("00001") && !permission.equals("11111")) {
                return "not_permission";
            } else {
                List admins = this.adminDao.findAdminsByPermission(admin_id.intValue(), "00000");
                request.setAttribute("admins", admins);
                return "success";
            }
        } else {
            return "un_login";
        }
    }

    public String channelView(Channel channel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(permission == null) {
            return "un_login";
        } else if(!permission.equals("00001") && !permission.equals("11111") && !permission.equals("00000")) {
            return "not_permission";
        } else {
            int count = this.channelDao.getCountByAdminId(admin_id.intValue(), permission, channel.getName());
            List channels = this.channelDao.getChannelByAdminId(admin_id.intValue(), channel.getId(), permission, channel.getName());
            int pageCount = (int)Math.ceil((double)count / 18.0D);
            request.setAttribute("permission", permission);
            request.setAttribute("channels", channels);
            request.setAttribute("count", Integer.valueOf(count));
            request.setAttribute("pageCount", Integer.valueOf(pageCount));
            request.setAttribute("thisPage", Integer.valueOf(channel.getId()));
            request.setAttribute("name", channel.getName());
            request.setAttribute("projectHost", this.config.getProjectHost());
            request.setAttribute("projectName", this.config.getProjectName());
            Calendar calendar = Calendar.getInstance();
            ArrayList days = new ArrayList();

            for(int i = 0; i < 7; ++i) {
                int month = calendar.get(2) + 1;
                int day = calendar.get(5);
                days.add(month + "月" + day + "日");
                calendar.roll(6, -1);
            }

            request.setAttribute("days", days);
            return "success";
        }
    }

    @Override
    public String channelViewCountUv(Channel channel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int id = channel.getId();
        if(id != -1 && session.getAttribute("cv" + id) == null) {
            Channel hold = this.channelDao.findById(id);
            hold.setToday_click(hold.getToday_click()+1);
            hold.setAll_click(hold.getAll_click()+1);
            session.setAttribute("cv" + id, "");
        }

        return "success";
    }

    public String altChannel(Channel channel, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(permission != null) {
            if(!permission.equals("00001") && !permission.equals("11111")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                Admin creater = this.adminDao.findById(admin_id.intValue());
                Admin viewer = this.adminDao.findById(channel.getViewer().getId());
                Channel hold = this.channelDao.findById(channel.getId());
                if(hold != null && viewer != null && viewer.getCreater().getId() == creater.getId() && hold.getCreater().getId() == admin_id.intValue()) {
                    if(this.channelDao.existName(channel.getName()) && !channel.getName().equals(hold.getName())) {
                        return "{\"hint\":\"already_exist\"}";
                    } else {
                        hold.setViewer(viewer);
                        hold.setName(channel.getName());
                        hold.setPrice(channel.getPrice());
                        return "{\"hint\":\"success\"}";
                    }
                } else {
                    return "{\"hint\":\"illegal_request\"}";
                }
            }
        } else {
            return "{\"hint\":\"un_login\"}";
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
                Channel hold = this.channelDao.findById(id);
                if(hold == null || hold.getCreater().getId() != admin_id.intValue() && !permission.equals("11111")) {
                    return "illegal_request";
                } else {
                    List admins = this.adminDao.findAdminsByPermission(admin_id.intValue(), "00000");
                    request.setAttribute("admins", admins);
                    request.setAttribute("channel", hold);
                    return "success";
                }
            }
        } else {
            return "un_login";
        }
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
