<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="http://dzez.v228.10000net.cn/css/weui.min.css">
    <link rel="stylesheet" href="http://dzez.v228.10000net.cn/css/jquery-weui.min.css">
    <script src="http://dzez.v228.10000net.cn/js/jquery.min.js"></script>
    <script src="http://dzez.v228.10000net.cn/js/jquery-weui.min.js"></script>
    <script src="http://dzez.v228.10000net.cn/js/swiper.min.js"></script>
    <script src="http://dzez.v228.10000net.cn/js/city-picker.min.js"></script>
    <script src="http://dzez.v228.10000net.cn/js/jquery.form.js" type="text/javascript"></script>
</head>
<style type="text/css">
    * {
        padding: 0;
        margin: 0;
    }

    ul li {
        list-style: none;
    }

    html {
        background: #F5F5F5;
    }

    ::-webkit-input-placeholder {
        color: #AAAAAA;
    }

    :-moz-placeholder {
        color: #AAAAAA;
    }

    ::-moz-placeholder {
        color: #AAAAAA;
    }

    :-ms-input-placeholder {
        color: #AAAAAA;
    }

    #top {
        width: 100vw;
        height: 13vw;
        background: #5F9BF1;
    }

    #select {
        width: 100vw;
        height: 13vw;
        background: #fff;
    }

    #select span {
        width: 50vw;
        float: left;
        display: block;
        height: 12.2vw;
        text-align: center;
        line-height: 12.2vw;
    }

    #content {
        width: 100vw;
        height: 98vw;
    }

    #content ul li {
        width: 100vw;
        height: 12vw;
        background: white;
        border-bottom: 1px solid #F6F6F6;
        float: left;
    }

    #content ul li .more {
        width: 10vw;
        float: right;
        height: 6vw;
        font-size: 2vw;
        line-height: 6vw;
        text-align: center;
        margin-top: 3vw;
    }

    #content ul li input {
        width: 80vw;
        float: left;
        height: 5vw;
        border: none;
        background: none;
        padding: 4vw 0 4vw 0;
        font-size: 3.5vw;
        text-indent: 5vw;
        float: left;
    }

    .regButton {
        width: 90vw;
        margin-left: 5vw;
        background: #5F9BF1;
        height: 11vw;
        font-size: 5vw;
        text-align: center;
        line-height: 11vw;
        border-radius: 5vw;
        margin-top: 6vw;
        color: white;
    }

    .bottom {
        width: 90vw;
        height: 5vw;
        margin-left: 5vw;
        font-size: 3.5vw;
        color: black;
        margin-top: 3vw;
        font-weight: 700;
    }

    .getCode {
        width: 27vw;
        height: 11vw;
        border-radius: 2vw;
        border: none;
        outline: none;
        color: #fff;
        float: right;
        background: #5F9BF1;
        font-size: 3vw;
        line-height: 11vw;
    }
</style>
<body onload="addBackListener();">
<div id="top">
    <span style="width: 10vw;height: 12vw;display: block;float: left;"><img onclick="window.location.href = 'auth_center.action';" src="images/tuihou.png"
                                                                            style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
    <p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">银行卡认证</p>
</div>

<div id="content">
    <form id="detail_form">
        <ul>
            <li>
                <input type="text" name="name" value="${name}" placeholder="姓名" maxlength="15"/>
            </li>

            <li>
                <input type="text" id="id_card" name="id_card" value="${idcard}" maxlength="18" placeholder="身份证号码" style="width: 80vw;"/>
            </li>

            <li>
                <input type="text" name="reserved_number" id="reserved_number" value="${reserved_number}" placeholder="预留号码" maxlength="11"/>

            </li>

            <li>
                <input type="tel" name="credit_number" id="bank_num" placeholder="银行卡号" maxlength="25" value="${credit_number}"/>
            </li>

            <li>
                <input type="text" name="credit_name" id="bank-picker" placeholder="开户银行" style="width: 80vw;" value="${credit_name}"/>
                <div class="more">
                    <img src="images/more.png" style="width: 6vw;"/>
                </div>
            </li>
            <input type="hidden" id="lend" name="lend" value="${lend}"/>
        </ul>
    </form>

    <form id="sms_form">
        <ul>
            <li style="width:73vw;">
                <input style="width: 73vw;" type="tel" id="validatecode" maxlength="6" name="validatecode" placeholder="请输入手机验证码"/>
            </li>
            <li style="width:27vw;float: right;">
                <button class="getCode" type="button">获取验证码</button>
            </li>
        </ul>
    </form>
</div>
<div class="regButton" onclick="submit();">
    下一步
</div>
<script type="text/javascript">

    //返回键处理
    function addBackListener() {
        document.addEventListener('plusready', function () {
            var webview = plus.webview.currentWebview();
            plus.key.addEventListener('backbutton', function () {
                webview.canBack(function (e) {
                    if (e.canBack) {
                        webview.back();
                    } else {
                        //webview.close(); //hide,quit
                        //plus.runtime.quit();
                        //首页返回键处理
                        //处理逻辑：1秒内，连续两次按返回键，则退出应用；
                        var first = null;
                        plus.key.addEventListener('backbutton', function () {
                            //首次按键，提示‘再按一次退出应用’
                            if (!first) {
                                first = new Date().getTime();
                                //console.log('再按一次退出应用');
                                setTimeout(function () {
                                    first = null;
                                }, 1000);
                            } else {
                                if (new Date().getTime() - first < 1500) {
                                    plus.runtime.quit();
                                }
                            }
                        }, false);
                    }
                })
            });
        });
    }

    //校验人卡信息，成功后返回验证码
    $('.getCode', "#sms_form").click(function () {
        var empty = false;
        $("input", "#detail_form").each(function (n) {
            if ($(this).val().trim() == "") {
                $.alert("请填写完整后提交", function () {
                    return;
                });
                empty = true;
            }
        });
        if (empty) {
            return;
        }

        var id_card = $("#id_card").val().trim();
        var idcardReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; //身份证号
        if (!idcardReg.test(id_card)) {
            $.alert("请输入正确的身份证号", function () {
                return;
            });
            return;
        }

        var phone = $("#reserved_number").val().trim();
        var phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;  //手机号正则表达式
        if (!phoneReg.test(phone)) {
            $.alert("请输入正确的手机号！", function () {
                return;
            });
            return;
        }

        var optionsInfo = {
            url: "../user_detail_alt.action",
            type: "post",
            dataType: "json",
            success: function (data) {
                var hint = data.hint;
                if (hint == "unionFirstPay_success") {
                    $("#lend").val("1");
                    $.confirm({
                        title: '提示',
                        text: '已发送验证码，请注意查收',
                        onOK: function () {
                        },
                        onCancel: function () {
                        }
                    });
                } else if (hint == "un_login") {
                    $.alert("登录超时，请重新登录", function () {
                        window.location.href = "../login.html";
                    });
                } else if (hint == "already_exist") {
                    $.alert("请勿重复提交", function () {
                        window.location.href = "auth_center.action";
                    });
                } else {
                    $.confirm({
                        title: '提示',
                        text: decodeURI(hint),
                        onOK: function () {
                        },
                        onCancel: function () {
                        }
                    });
                }

            }
        };

        //短信重发
        var optionsResend = {
            url: "../user_detail_smsResend.action",
            type: "post",
            dataType: "json",
            success: function (data) {
                var hint = data.hint;
                if (hint == "firstPaySmsResend_success") {
                    $.confirm({
                        title: '提示',
                        text: '已发送验证码，请注意查收',
                        onOK: function () {
                        },
                        onCancel: function () {
                        }
                    });
                } else if (hint == "un_login") {
                    $.alert("登录超时，请重新登录", function () {
                        window.location.href = "../login.html";
                    });
                } else if (hint == "already_exist") {
                    $.alert("请勿重复提交", function () {
                        window.location.href = "auth_center.action";
                    });
                } else if (hint == "too_frequently") {
                    $.alert("操作频繁，请60秒后再试！", function () {

                    });
                } else if (hint == "re_try") {
                    $.alert("操作超时，请重试！", function () {

                    });
                    $("#lend").val("0");
                } else {
                    $.confirm({
                        title: '提示',
                        text: decodeURI(hint),
                        onOK: function () {
                        },
                        onCancel: function () {
                        }
                    });
                }

            }
        };

        //如果lend=1，表示四项认证成功，待短信验证
        var lend = $("#lend").val().trim();

        if (lend == 0) {
            $("#detail_form").ajaxSubmit(optionsInfo);
        } else {
            $("#detail_form").ajaxSubmit(optionsResend);
        }


    });

    //校验验证码，代扣
    function submit() {
        var empty = false;
        $("input", "#content").each(function (n) {
            if ($(this).val().trim() == "") {
                $.alert("请填写完整后获取验证码", function () {
                    return;
                });
                empty = true;
            }
        });
        if (empty) {
            return;
        }

        //如果lend=1，表示四项认证成功，待短信确认
        var lend = $("#lend").val().trim();
        if (lend != 1) {
            $.confirm({
                title: '提示',
                text: '请获取验证码',
                onOK: function () {
                },
                onCancel: function () {
                }
            });
            return;
        }

        //短信确认接口
        var options = {
            url: "../user_detail_smsConfirm.action",
            type: "post",
            dataType: "json",
            success: function (data) {
                var hint = data.hint;
                if (hint == "success") {
                    window.location.href = "auth_center.action";
                } else if (hint == "un_login") {
                    $.alert("登录超时，请重新登录", function () {
                        window.location.href = "../login.html";
                    });
                } else if (hint == "already_exist") {
                    $.alert("请勿重复提交", function () {
                        window.location.href = "auth_center.action";
                    });
                } else {
                    $.confirm({
                        title: '提示',
                        text: decodeURI(hint),
                        onOK: function () {
                        },
                        onCancel: function () {
                        }
                    });
                }

            }
        };

        $("#sms_form").ajaxSubmit(options);

    }

    $("#bank-picker").picker({
        title: "请选择所属银行",
        cols: [
            {
                textAlign: 'center',
                values: ["中国建设银行", "中国工商银行", "中国农业银行", "招商银行", "九江银行", "中国广大银行", "中信银行", "交通银行", "兴业银行", "江西银行", "上海浦发银行", "农村信用社", "中国民生银行", "华夏银行", "恒丰银行", "浙商银行", "深圳发展银行", "广东发展银行", "中资银行", "北京商业银行", "上海银行", "济南商业银行"]
            }
        ]
    });

    var str = {
        "SRCB": "深圳农村商业银行",
        "BGB": "广西北部湾银行",
        "SHRCB": "上海农村商业银行",
        "BJBANK": "北京银行",
        "WHCCB": "威海市商业银行",
        "BOZK": "周口银行",
        "KORLABANK": "库尔勒市商业银行",
        "SPABANK": "平安银行",
        "SDEB": "顺德农商银行",
        "HURCB": "湖北省农村信用社",
        "WRCB": "无锡农村商业银行",
        "BOCY": "朝阳银行",
        "CZBANK": "浙商银行",
        "HDBANK": "邯郸银行",
        "BOC": "中国银行",
        "BOD": "东莞银行",
        "CCB": "中国建设银行",
        "ZYCBANK": "遵义市商业银行",
        "SXCB": "绍兴银行",
        "GZRCU": "贵州省农村信用社",
        "ZJKCCB": "张家口市商业银行",
        "BOJZ": "锦州银行",
        "BOP": "平顶山银行",
        "HKB": "汉口银行",
        "SPDB": "上海浦东发展银行",
        "NXRCU": "宁夏黄河农村商业银行",
        "NYNB": "广东南粤银行",
        "GRCB": "广州农商银行",
        "BOSZ": "苏州银行",
        "HZCB": "杭州银行",
        "HSBK": "衡水银行",
        "HBC": "湖北银行",
        "JXBANK": "嘉兴银行",
        "HRXJB": "华融湘江银行",
        "BODD": "丹东银行",
        "AYCB": "安阳银行",
        "EGBANK": "恒丰银行",
        "CDB": "国家开发银行",
        "TCRCB": "江苏太仓农村商业银行",
        "NJCB": "南京银行",
        "ZZBANK": "郑州银行",
        "DYCB": "德阳商业银行",
        "YBCCB": "宜宾市商业银行",
        "SCRCU": "四川省农村信用",
        "KLB": "昆仑银行",
        "LSBANK": "莱商银行",
        "YDRCB": "尧都农商行",
        "CCQTGB": "重庆三峡银行",
        "FDB": "富滇银行",
        "JSRCU": "江苏省农村信用联合社",
        "JNBANK": "济宁银行",
        "CMB": "招商银行",
        "JINCHB": "晋城银行JCBANK",
        "FXCB": "阜新银行",
        "WHRCB": "武汉农村商业银行",
        "HBYCBANK": "湖北银行宜昌分行",
        "TZCB": "台州银行",
        "TACCB": "泰安市商业银行",
        "XCYH": "许昌银行",
        "CEB": "中国光大银行",
        "NXBANK": "宁夏银行",
        "HSBANK": "徽商银行",
        "JJBANK": "九江银行",
        "NHQS": "农信银清算中心",
        "MTBANK": "浙江民泰商业银行",
        "LANGFB": "廊坊银行",
        "ASCB": "鞍山银行",
        "KSRB": "昆山农村商业银行",
        "YXCCB": "玉溪市商业银行",
        "DLB": "大连银行",
        "DRCBCL": "东莞农村商业银行",
        "GCB": "广州银行",
        "NBBANK": "宁波银行",
        "BOYK": "营口银行",
        "SXRCCU": "陕西信合",
        "GLBANK": "桂林银行",
        "BOQH": "青海银行",
        "CDRCB": "成都农商银行",
        "QDCCB": "青岛银行",
        "HKBEA": "东亚银行",
        "HBHSBANK": "湖北银行黄石分行",
        "WZCB": "温州银行",
        "TRCB": "天津农商银行",
        "QLBANK": "齐鲁银行",
        "GDRCC": "广东省农村信用社联合社",
        "ZJTLCB": "浙江泰隆商业银行",
        "GZB": "赣州银行",
        "GYCB": "贵阳市商业银行",
        "CQBANK": "重庆银行",
        "DAQINGB": "龙江银行",
        "CGNB": "南充市商业银行",
        "SCCB": "三门峡银行",
        "CSRCB": "常熟农村商业银行",
        "SHBANK": "上海银行",
        "JLBANK": "吉林银行",
        "CZRCB": "常州农村信用联社",
        "BANKWF": "潍坊银行",
        "ZRCBANK": "张家港农村商业银行",
        "FJHXBC": "福建海峡银行",
        "ZJNX": "浙江省农村信用社联合社",
        "LZYH": "兰州银行",
        "JSB": "晋商银行",
        "BOHAIB": "渤海银行",
        "CZCB": "浙江稠州商业银行",
        "YQCCB": "阳泉银行",
        "SJBANK": "盛京银行",
        "XABANK": "西安银行",
        "BSB": "包商银行",
        "JSBANK": "江苏银行",
        "FSCB": "抚顺银行",
        "HNRCU": "河南省农村信用",
        "COMM": "交通银行",
        "XTB": "邢台银行",
        "CITIC": "中信银行",
        "HXBANK": "华夏银行",
        "HNRCC": "湖南省农村信用社",
        "DYCCB": "东营市商业银行",
        "ORBANK": "鄂尔多斯银行",
        "BJRCB": "北京农村商业银行",
        "XYBANK": "信阳银行",
        "ZGCCB": "自贡市商业银行",
        "CDCB": "成都银行",
        "HANABANK": "韩亚银行",
        "CMBC": "中国民生银行",
        "LYBANK": "洛阳银行",
        "GDB": "广东发展银行",
        "ZBCB": "齐商银行",
        "CBKF": "开封市商业银行",
        "H3CB": "内蒙古银行",
        "CIB": "兴业银行",
        "CRCBANK": "重庆农村商业银行",
        "SZSBK": "石嘴山银行",
        "DZBANK": "德州银行",
        "SRBANK": "上饶银行",
        "LSCCB": "乐山市商业银行",
        "JXRCU": "江西省农村信用",
        "ICBC": "中国工商银行",
        "JZBANK": "晋中市商业银行",
        "HZCCB": "湖州市商业银行",
        "NHB": "南海农村信用联社",
        "XXBANK": "新乡银行",
        "JRCB": "江苏江阴农村商业银行",
        "YNRCC": "云南省农村信用社",
        "ABC": "中国农业银行",
        "GXRCU": "广西省农村信用",
        "PSBC": "中国邮政储蓄银行",
        "BZMD": "驻马店银行",
        "ARCU": "安徽省农村信用社",
        "GSRCU": "甘肃省农村信用",
        "LYCB": "辽阳市商业银行",
        "JLRCU": "吉林农信",
        "URMQCCB": "乌鲁木齐市商业银行",
        "XLBANK": "中山小榄村镇银行",
        "CSCB": "长沙银行",
        "JHBANK": "金华银行",
        "BHB": "河北银行",
        "NBYZ": "鄞州银行",
        "LSBC": "临商银行",
        "BOCD": "承德银行",
        "SDRCU": "山东农信",
        "NCB": "南昌银行",
        "TCCB": "天津银行",
        "WJRCB": "吴江农商银行",
        "CBBQS": "城市商业银行资金清算中心",
        "HBRCU": "河北省农村信用社"
    };


    $("#bank_num").blur(function () {
        var num = $(this).val().replace(/ /g, '');
        ;
        $.ajax({
            url: "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json",
            data: "cardBinCheck=true&cardNo=" + num,
            type: "GET",
            dataType: "json",
            success: function (data) {
                if (data.validated == true) {
                    var bankName = str[data.bank];
                    $("#bank-picker").val(bankName);
                } else {
                    $.alert("请输入正确的银行卡号", function () {
                        $("#bank_num").focus();
                    });
                }
            }
        });
    });

</script>
</body>
</html>
