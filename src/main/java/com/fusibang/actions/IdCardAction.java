//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fusibang.services.IdCardService;
import com.fusibang.tables.IdCard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class IdCardAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<IdCard> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private IdCard idCard;
    private IdCardService idCardService;
    private String fileFileName;
    private File file;

    public IdCardAction() {
    }

    public String view() {
        return this.idCardService.view(this.request);
    }

    public String frontImage() {
        HttpSession session = this.request.getSession();
        String phone = (String)session.getAttribute("u");
        if(phone != null) {
            ServletOutputStream os = null;
            FileInputStream is = null;

            try {
                String e = this.request.getRealPath("/") + "\\WEB-INF\\auth";
                String fileName = phone + "front." + "jpg";
                File file = new File(e, fileName);
                if(file.exists()) {
                    os = this.response.getOutputStream();
                    is = new FileInputStream(file);
                    byte[] buffer = new byte[500];

                    while(-1 != is.read(buffer, 0, buffer.length)) {
                        os.write(buffer);
                        os.flush();
                    }
                }
            } catch (IOException var21) {
                var21.printStackTrace();
            } finally {
                if(os != null) {
                    try {
                        os.close();
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    }
                }

                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }
                }

            }
        }

        return null;
    }

    public String backImage() {
        HttpSession session = this.request.getSession();
        String phone = (String)session.getAttribute("u");
        if(phone != null) {
            ServletOutputStream os = null;
            FileInputStream is = null;

            try {
                String e = this.request.getRealPath("/") + "\\WEB-INF\\auth";
                String fileName = phone + "back." + "jpg";
                File file = new File(e, fileName);
                if(file.exists()) {
                    os = this.response.getOutputStream();
                    is = new FileInputStream(file);
                    byte[] buffer = new byte[500];

                    while(-1 != is.read(buffer, 0, buffer.length)) {
                        os.write(buffer);
                        os.flush();
                    }
                }
            } catch (IOException var21) {
                var21.printStackTrace();
            } finally {
                if(os != null) {
                    try {
                        os.close();
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    }
                }

                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }
                }

            }
        }

        return null;
    }

    public String authFront() {
        HttpSession session = this.request.getSession();
        String phone = (String)session.getAttribute("u");
        String result;
        if(phone != null) {
            if(this.file.length() < 20971520L) {
                String e = this.request.getRealPath("/") + "\\WEB-INF\\auth";

                try {
                    FileInputStream e1 = new FileInputStream(this.file);
                    this.fileFileName = phone + "front." + "jpg";
                    File hold = new File(e, this.fileFileName);
                    FileOutputStream os = new FileOutputStream(hold);
                    byte[] buffer = new byte[500];

                    while(-1 != e1.read(buffer, 0, buffer.length)) {
                        os.write(buffer);
                        os.flush();
                    }

                    os.close();
                    e1.close();
                    result = this.idCardService.authFront(this.request, hold.toString());
                } catch (Exception var10) {
                    var10.printStackTrace();
                    result = "{\"hint\":\"unknow_error\"}";
                }
            } else {
                result = "{\"hint\":\"file_too_large\"}";
            }
        } else {
            result = "{\"hint\":\"un_login\"}";
        }

        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(result);
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return null;
    }

    public String authBack() {
        HttpSession session = this.request.getSession();
        String phone = (String)session.getAttribute("u");
        String result;
        if(phone != null) {
            if(this.file.length() < 20971520L) {
                String e = this.request.getRealPath("/") + "\\WEB-INF\\auth";

                try {
                    FileInputStream e1 = new FileInputStream(this.file);
                    this.fileFileName = phone + "back." + "jpg";
                    File hold = new File(e, this.fileFileName);
                    FileOutputStream os = new FileOutputStream(hold);
                    byte[] buffer = new byte[500];

                    while(-1 != e1.read(buffer, 0, buffer.length)) {
                        os.write(buffer);
                        os.flush();
                    }

                    os.close();
                    e1.close();
                    result = this.idCardService.authBack(this.request, hold.toString());
                } catch (Exception var10) {
                    var10.printStackTrace();
                    result = "{\"hint\":\"unknow_error\"}";
                }
            } else {
                result = "{\"hint\":\"file_too_large\"}";
            }
        } else {
            result = "{\"hint\":\"un_login\"}";
        }

        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(result);
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return null;
    }

    public String addFont() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.idCardService.saveFont(session, this.idCard));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String addBack() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.idCardService.saveBack(session, this.idCard));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public void setIdCardService(IdCardService idCardService) {
        this.idCardService = idCardService;
    }

    public IdCard getModel() {
        if(this.idCard == null) {
            ;
        }

        this.idCard = new IdCard();
        return this.idCard;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
}
