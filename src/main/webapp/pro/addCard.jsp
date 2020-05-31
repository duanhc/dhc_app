<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		a{text-decoration: none;}
		ul li{list-style: none;}
		html{background: #f9f9f9;}
		::-webkit-input-placeholder { color: #959595; } :-moz-placeholder { color: #959595; } ::-moz-placeholder { color: #959595; } :-ms-input-placeholder { color: #959595; }  
		#top{width: 100vw;height: 12vw;background: linear-gradient(to right,#00a3ff,#0074ff);text-align: center;line-height: 12vw;font-size: 3.5vw;color: #fff;}
		.item{width: 100vw;height: 28vw;background: linear-gradient(to right,#00a3ff,#0074ff);position: relative;z-index: 5;}
		.item .item_pic{position: absolute;top:3vw;right: 20vw;}
		
		.message{width: 90vw;margin-left: 5vw;height: 24vw;border: 1px solid #DEDEDE;margin-top: 3vw;box-shadow: 2px  2px 3px #eee;border-radius: 3vw;}
		.message ul li{width: 90vw;height: 12vw;}
		.message ul li .mess_left{width: 20vw;height: 10vw;float: left;line-height: 12vw;font-size: 4vw;text-align: left;text-indent: 5vw;}
		.message ul li .mess_right{width: 65vw;height: 10vw;float: left;line-height: 12vw;font-size: 4vw;text-align: right;margin-right: 5vw;}
		
		
		#content{width: 80vw;padding-left: 10vw;padding-right: 10vw;height: 50vw;margin-top: 5vw;}
		#content ul li{width: 80vw;height: 12vw;border-bottom: 1px solid #DEDEDE;margin-bottom: 2vw;}
   		#content ul li .cont_left{width: 25vw;height: 12vw;float: left;font-size: 4vw;line-height: 10vw;}
   		#content ul li .cont_right{width: 55vw;height: 12vw;float: left;}
   		#content ul li .cont_right input{height: 4vw;border: none;background: none;padding: 3vw 0 3vw 0;font-size: 4vw;float: left;line-height: 10vw;}

		.regButton{width: 66vw;height: 11vw;line-height: 11vw;margin-left: 17vw;font-size: 3.5vw;background: linear-gradient(to right,#00a3ff,#0074ff);color: #fff;text-align: center;border-radius: 5vw;margin-top: 5vw;}
		#hidebg { position:absolute;left:0px;top:0px;
	      background-color:#000;
	      width:100%;  
	      filter:alpha(opacity=40);  
	      opacity:0.4;  
	      display:none; 
	      z-Index:555;
	      height: 100%;}
      #hidebox{width: 60vw;height: 60vw;background: white;position: fixed;top: 55vw;z-index: 999;left: 20vw;border-radius: 3vw;display: none;}
		#ipt{width: 50vw;padding-left: 6vw;padding-right: 2vw;height: 10vw;margin-top: 5vw;}
		#ipt input{width: 6vw;margin-right: 0.5vw;height: 7vw;border: 1px solid #686868;text-align: center;}
      #hidebox .quxiao,#hidebox .queding{width: 34.8vw;height: 13vw;float: left;margin-top: 6vw;line-height: 13vw;text-align: center;color: #468FFF;border-top: 1px solid #DEDEDE;}
	</style>
	<link rel="stylesheet" type="text/css" href="../css/jquery-weui.min.css"/>
    <link rel="stylesheet" href="../css/weui.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery-weui.min.js"></script>
    <script src="../js/swiper.min.js"></script>
    <script src="../js/city-picker.min.js"></script>
    <script src="../js/jquery.form.js" type="text/javascript"></script>
	<body>
		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;" onclick="window.open('jssh.html','_self');"><img src="images/tuihou.png" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4vw;">添加银行卡</p>
		</div>
		<div class="item">
			<p style="color: #fff;text-indent: 10vw;font-size: 6vw;line-height: 10vw;float: left;margin-top: 5vw;width: 80vw;">添加收款银行卡</p>
			<p style="color: #FFFFFF;text-indent: 10vw;font-size: 3.5vw;float: left;margin-top: 2vw;">您的信息将被严格保密,请放心添加</p>
			<span class="item_pic"><img src="images/addcard.png" style="width: 15vw;"/></span>
		</div>
		
		<div class="message">
			<ul>
				<li>
					<div class="mess_left">
						姓名
					</div>
					<div class="mess_right">
						${name}
					</div>
				</li>
				<li>
					<div class="mess_left">
						身份证
					</div>
					<div class="mess_right">
                        ${idcard}
					</div>
				</li>
			</ul>
		</div>
		
		<div id="content">
            <form id="detail_form">
                <ul>
                    <li>
                        <div class="cont_left">
                            选择银行：
                        </div>
                        <div class="cont_right">
                            <input type="text" name="credit_name" id="bank-picker" placeholder="请选择" style="text-align: right;width: 50vw;"/>
                            <div class="more">
                                <img src="images/more.png" style="width: 5vw;margin-top: 2.2vw;"/>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="cont_left">
                            银行卡号：
                        </div>
                        <div class="cont_right">
                            <input type="tel" name="credit_number" id="bank_num" maxlength="25" placeholder="请输入您的银行卡号"/>

                        </div>
                    </li>
                    <li>
                        <div class="cont_left">
                            预留手机号：
                        </div>
                        <div class="cont_right">
                            <input type="text" name="reserved_number" id="bank_phone" maxlength="11" placeholder="请输入您银行预留手机号"/>
                        </div>
                    </li>
                </ul>
                <%--    手机验证码         --%>
                <input type="hidden" name="code" id="code" value="111111"/>
            </form>
		</div>
		
		<div class="regButton" onclick="finish()">
			完成
		</div>
		
		<div id="hidebg"></div>
		<div id="hidebox">
			<p style="text-align: center;margin-top: 10vw;font-size: 4vw;">正在验证身份证有效性</p>
			
			<p style="text-align: center;margin-top: 4vw;font-size: 4vw;color: #686868;">请输入短信验证码</p>
			<div id="ipt">
				<input type="text" class="ipts" maxlength="1" pattern="\d*" id="ipt1" style="border: 1px solid #db9942;">
                <input type="text" class="ipts" maxlength="1" pattern="\d*" id="ipt2">
                <input type="text" class="ipts" maxlength="1" pattern="\d*" id="ipt3">
                <input type="text" class="ipts" maxlength="1" pattern="\d*" id="ipt4">
                <input type="text" class="ipts" maxlength="1" pattern="\d*" id="ipt5">
                <input type="text" class="ipts" maxlength="1" pattern="\d*" id="ipt6">
			</div>
			<p id="reSendMsg" style="color: #686868;font-size: 4vw;text-align: center;border: none;outline: none;">重新发送</p>
			<p style="font-size: 4vw;text-align: center;margin-top: 3vw;display: none" id="seconds">(60秒)</p>
			<div class="cha" style="position: absolute;bottom: -20vw;left: 27vw;" >
				<img src="images/cha.png" style="width: 6vw;"/>
			</div>
		</div>
		
	</body>
	<script type="text/javascript">
		var flag = 1;
		$('.noicon').click(function(){
			if(flag == 1){
				$('.noicon').removeClass('noicon').addClass('icon');
				flag = 0;
			}else{
				$('.icon').removeClass('icon').addClass('noicon');
				flag = 1;
			}
		})

		 $("#bank-picker").picker({
            title: "请选择所属银行",
            cols: [
                {
                    textAlign: 'center',
                    values: ["中国建设银行", "中国工商银行", "中国农业银行","中国银行","中国邮政银行", "招商银行", "九江银行", "中国光大银行", "中信银行", "交通银行", "兴业银行", "江西银行", "上海浦发银行", "农村信用社", "中国民生银行", "华夏银行", "恒丰银行", "浙商银行", "深圳发展银行", "广东发展银行", "中资银行", "北京商业银行", "上海银行", "济南商业银行","其他类银行"]
                }
            ]
        });

		var flag = true;
		function finish() {
		    //校验数据
            var empty = false;
            $("input",$("#content")).each(function (n) {
                if ($(this).val().trim() == "") {
                    empty = true;
                }
            });
            if (empty) {
                $.alert("请填写完整后提交！",function(){
                });
                return;
            }

            if(countdown == 60 && flag){
                //发送验证码
                var isSuccess = sendMsg();
                if(!isSuccess){
                    return;
                }
                flag = false;
                $("#seconds").show();
                invokeSettime($("#seconds"));
            }else if(countdown == 60){
                $("#seconds").hide();
            }

            show();
        }

        function sendMsg() {
		    var phone = $("#bank_phone").val().trim();
            var phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;  //手机号正则表达式
            if(!phoneReg.test(phone)){
                $.alert("请输入正确的手机号！",function(){
                });
                return false;
            }

            $.ajax({
                url : "phone_bankcard.do",
                type : "GET",
                data : "phone=" + phone,
                dataType : "json",
                success : function(data) {
                    var hint = data.hint;
                    if (hint == "success") {
                        // $.alert("发送成功",function(){
                        // });
                    }else if (hint == "too_frequently") {
                        $.alert("发送频繁，请稍后再试",function(){

                        });
                    } else if (hint == "unknow_error") {
                        $.alert("未知错误",function(){

                        });
                    }else if(hint == "un_login"){
                        $.alert("登录超时,请重新登录", function (){
                            window.location.href = "../login.html";
                        });
                    };
                }
            });

            return true;
        }

        function show()  //显示隐藏层和弹出层
		{
		   document.getElementById("hidebox").style.display="block";  //显示弹出层
		}
		function hide()  //去除隐藏层和弹出层
		{
		   document.getElementById("hidebox").style.display="none";
		}
		
        $(".cha").click(function(){
        	clearInterval($("#seconds"));
        	hide();
        })
        
        function cleartime(){
        	clearInterval(time);
        }

        var countdown=60;
		function invokeSettime(obj){
		    if(countdown == 60){
                settime(obj);
                function settime(obj) {

                    if (countdown == 0) {
                        //允许重新发送
                        $("#reSendMsg").css("color","#060606");
                        obj.hide();
                        countdown = 60;
                        return;
                    } else {
                        $("#reSendMsg").css("color","#686868");
                        obj.text(countdown + "秒");
                        countdown--;
                    }
                    setTimeout(function() {
                        settime(obj);
                    },1000);
                }
            }
		}

        $("#reSendMsg").click(function () {
            if(countdown == 60){
                flag = true;
                finish();
            }
        })


        var active = 0;
        var inputBtn = document.querySelectorAll('.ipts');
        for (var i = 0; i < inputBtn.length; i++) {
            inputBtn[i].addEventListener('click', function () {
                inputBtn[active].focus();
            }, false);
            inputBtn[i].addEventListener('focus', function () {
                this.addEventListener('keyup', listenKeyUp, false);
            }, false);
            inputBtn[i].addEventListener('blur', function () {
                this.removeEventListener('keyup', listenKeyUp, false);
            }, false);
        }

        /**
         * 监听键盘的敲击事件
         */
        function listenKeyUp() {
            if (/^[0-9]*$/g.test(this.value)) {
                if (!isNaN(this.value) && this.value.length != 0) {
                    if (active < 5) {
                        active += 1;
                    }
                    inputBtn[active].focus();
                } else if (this.value.length == 0) {
                    if (active > 0) {
                        active -= 1;
                    }
                    inputBtn[active].focus();
                }
                if (active >= 5) {
                    //提交信息，校验验证码
                    if($('#ipt6').val().trim() != ""){
                        var codeSix = $('#ipt1').val() + $('#ipt2').val() + $('#ipt3').val() + $('#ipt4').val() + $('#ipt5').val() + $('#ipt6').val();
                        $("#code").val(codeSix);
                        //校验验证码
                        var options = {
                            url: "../user_detail_alt.action",
                            type: "post",
                            dataType: "json",
                            success: function (data) {
                                var hint = data.hint;
                                if (hint == "success") {
                                    //身份验证完成
                                    $.confirm({
                                        title: '提示',
                                        text: '提交成功，前往借款',
                                        onOK: function () {
                                            window.location.href = "lendmoney.html";
                                        },
                                        onCancel: function () {
                                            window.location.href = "jssh.html";
                                        }
                                    });
                                } else if (hint == "un_login") {
                                    $.alert("登录超时，请重新登录", function () {
                                        window.location.href = "../login.html";
                                    });
                                } else if (hint == "phone_code_error") {
                                    $.alert("验证码错误", function () {
                                    });
                                }else if (hint == "already_exist") {
                                    $.alert("请勿重复认证", function () {
                                    });
                                }

                            }
                        };

                        $("#detail_form").ajaxSubmit(options);
                    }
                }
            }
        }

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
</html>
