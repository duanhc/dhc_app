//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.help.YeePayHelp;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 易宝支付
 */
public class YeePayAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private YeePayHelp yeePayHelp;

    public YeePayAction() {}

    /**
     * 首次支付请求
     */
    public void unionFirstPay() {

        String requestno = format(request.getParameter("requestno"));
        String identityid = format(request.getParameter("identityid"));
        String identitytype = format(request.getParameter("identitytype"));
        String cardno = format(request.getParameter("cardno"));
        String idcardno = format(request.getParameter("idcardno"));
        String idcardtype = format(request.getParameter("idcardtype"));
        String username = format(request.getParameter("username"));
        String phone = format(request.getParameter("phone"));
        String amount = format(request.getParameter("amount"));
        String authtype = format(request.getParameter("authtype"));
        String issms = format(request.getParameter("issms"));
        String advicesmstype = format(request.getParameter("advicesmstype"));
        String smstempldatemsg = format(request.getParameter("smstempldatemsg"));
        String smstemplateid = format(request.getParameter("smstemplateid"));
        String avaliabletime = format(request.getParameter("avaliabletime"));
        String callbackurl = format(request.getParameter("callbackurl"));
        String requesttime = format(request.getParameter("requesttime"));
        String productname = format(request.getParameter("productname"));
        String terminalno = format(request.getParameter("terminalno"));
        String dividecallbackurl = format(request.getParameter("dividecallbackurl"));
        String newdividejstr = format(request.getParameter("newdividejstr"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("requestno", requestno);
        map.put("identityid", identityid);
        map.put("identitytype", identitytype);
        map.put("cardno", cardno);
        map.put("idcardno", idcardno);
        map.put("idcardtype", idcardtype);
        map.put("username", username);
        map.put("phone", phone);
        map.put("amount", amount);
        map.put("authtype", authtype);
        map.put("issms", issms);
        map.put("advicesmstype", advicesmstype);
        map.put("smstempldatemsg", smstempldatemsg);
        map.put("smstemplateid", smstemplateid);
        map.put("avaliabletime", avaliabletime);
        map.put("callbackurl", callbackurl);
        map.put("requesttime", requesttime);
        map.put("productname", productname);
        map.put("terminalno", terminalno);
        map.put("dividecallbackurl", dividecallbackurl);
        map.put("newdividejstr", newdividejstr);

        try {
            this.response.setContentType("text/json; charset=utf-8");
            response.getWriter().println(yeePayHelp.unionFirstPay(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首次支付短验确认
     */
    public void firstPaySmsConfirm() {
        String requestno = format(request.getParameter("requestno"));
        String validatecode = format(request.getParameter("validatecode"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("requestno", requestno);
        map.put("validatecode", validatecode);

        try {
            this.response.setContentType("text/json; charset=utf-8");
            response.getWriter().println(yeePayHelp.firstPaySmsConfirm(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@formatter:off
    /**
     * 首次支付短验重发
     *
     * 在首次支付过程中，如果商户没有收到短验，调用本接口重发短验
     *
     * 1.短验码格式： 6 位随机数字
     * 2.短验码有效期： 30 分钟
     * 3.短验码验证错误次数限制： 在有效期内允许试错 3 次， 3 次之后失效
     * 4.同一订单号的同一手机号限制： 在有效期内最多能发送 5 次相同的短验码
     * 5.发送最短间隔限制： 大于 50 秒
     */
    // @formatter:on
    public void firstPaySmsReSend() {
        String requestno = format(request.getParameter("requestno"));
        String advicesmstype = format(request.getParameter("advicesmstype"));

        Map<String, String> map = new HashMap<String, String>();
        // map.put("merchantno", merchantno);
        map.put("requestno", requestno);
        map.put("advicesmstype", advicesmstype);

        try {
            this.response.setContentType("text/json; charset=utf-8");
            response.getWriter().println(yeePayHelp.firstPaySmsReSend(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付回调接口
     * 
     * @return
     */
    public String payNotify() {
        try {
            this.response.getWriter().write(yeePayHelp.payNotify(this.request));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public static String format(String text) {
        return text == null ? "" : text.trim();
    }

    public void setYeePayHelp(YeePayHelp yeePayHelp) {
        this.yeePayHelp = yeePayHelp;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
