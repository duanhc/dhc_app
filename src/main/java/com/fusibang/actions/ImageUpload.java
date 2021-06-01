//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUpload extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private String fileFileName;
    private File file;

    public ImageUpload() {
    }

    public String execute() throws Exception {
        String permission = (String)this.request.getSession().getAttribute("ap");
        String result;
        if(permission != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                result = "{\"hint\":\"not_permission\"}";
            } else if(this.file.length() < 102400L) {
                String path = this.request.getRealPath("/") + "\\upload";
//                String path = this.request.getRealPath("/") + "upload";

                try {
                    FileInputStream e = new FileInputStream(this.file);
                    String category = this.fileFileName.split("\\.")[1];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String str = sdf.format(new Date());
                    this.fileFileName = str + "." + category;
                    FileOutputStream os = new FileOutputStream(new File(path, this.fileFileName));
                    byte[] buffer = new byte[500];

                    while(-1 != e.read(buffer, 0, buffer.length)) {
                        os.write(buffer);
                    }

                    os.close();
                    e.close();
                    result = "{\"fileName\":\"" + this.fileFileName + "\"}";
                } catch (FileNotFoundException var10) {
                    var10.printStackTrace();
                    result = "{\"hint\":\"unknow_error\"}";
                }
            } else {
                result = "{\"hint\":\"file_too_large\"}";
            }
        } else {
            result = "{\"hint\":\"un_login\"}";
        }

        this.response.getWriter().write(result);
        return null;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
        this.response.setContentType("application/json; charset=utf-8");
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
