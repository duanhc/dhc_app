//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fusibang.dao.UserDao;
import com.fusibang.help.AlipayConfig;
import com.fusibang.help.PayHelp;
import com.fusibang.services.AliPayService;
import com.fusibang.tables.Pay;
import com.fusibang.tables.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AliPayServiceImp implements AliPayService {
    private static final Logger logger = Logger.getLogger(AliPayServiceImp.class);
    private UserDao userDao;
    private PayHelp payHelp;

    public AliPayServiceImp() {}

    public String returnUrl(HttpServletRequest request) throws Exception {
        logger.info("recept alipay return url");
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if (id != null && (user = this.userDao.findById(id.intValue())) != null) {

            //获取支付宝GET过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verify_result = false;
            try {
                verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
            } catch (AlipayApiException e) {
                e.printStackTrace();
                //验证失败
                logger.error("return url valid fail! out_trade_no:"+out_trade_no+" verify_result:"+verify_result);
            }

            if(verify_result){
                //验证成功
                Pay pay = new Pay();
                pay.setIndent_str(out_trade_no);
                pay.setOrder_str(trade_no);
                this.payHelp.insertPay(pay, id);

            }else{
                //验证失败
                logger.error("return url valid fail! out_trade_no:"+out_trade_no);
            }

            return "success";
        } else {
            return "un_login";
        }
    }

    public void resultNotify(HttpServletRequest request) throws Exception{
        logger.info("recept alipay notification");
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        //金额
        String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if(verify_result){
            //验证成功
            this.payHelp.updatePay(out_trade_no, trade_no, Float.valueOf(totalAmount));

        }else{
            //验证失败
            logger.error("resultNotify valid fail! out_trade_no:"+out_trade_no);
        }

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPayHelp(PayHelp payHelp) {
        this.payHelp = payHelp;
    }

}
