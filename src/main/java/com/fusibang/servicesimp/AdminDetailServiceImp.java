//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.dao.AdminDetailDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.AdminDetailService;
import com.fusibang.tables.AdminDetail;

public class AdminDetailServiceImp extends ResponseStatus implements AdminDetailService {
    private AdminDetailDao adminDetailDao;

    public AdminDetailServiceImp() {
    }

    public String detailView(AdminDetail adminDetail, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if(permission == null) {
            return "un_login";
        } else if(!permission.equals("11111")) {
            return "not_permission";
        } else {
            int count = this.adminDetailDao.getCountByAdminId(adminDetail.getPermission(), adminDetail.getReal_name(), adminDetail.getForbid());
            int pageCount = (int)Math.ceil((double)count / 18.0D);
            List adminDetails = this.adminDetailDao.getAdminsDetailByAdminId(adminDetail.getId(), adminDetail.getPermission(), adminDetail.getReal_name(), adminDetail.getForbid());
            request.setAttribute("admins", adminDetails);
            request.setAttribute("count", Integer.valueOf(count));
            request.setAttribute("pageCount", Integer.valueOf(pageCount));
            request.setAttribute("thisPage", Integer.valueOf(adminDetail.getId()));
            request.setAttribute("name", adminDetail.getName());
            Calendar calendar = Calendar.getInstance();
            ArrayList days = new ArrayList();

            for(int i = 0; i < 4; ++i) {
                int month = calendar.get(2) + 1;
                int day = calendar.get(5);
                days.add(month + "月" + day + "日");
                calendar.roll(6, -1);
            }

            request.setAttribute("days", days);
            return "success";
        }
    }

    public void setAdminDetailDao(AdminDetailDao adminDetailDao) {
        this.adminDetailDao = adminDetailDao;
    }
}
