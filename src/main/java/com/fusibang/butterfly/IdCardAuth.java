//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.butterfly;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class IdCardAuth {
    public static final String APP_ID = "16451907";
    public static final String API_KEY = "o77UXxGr55n0uEEd4UcQpbSG";
    public static final String SECRET_KEY = "oT0tQEzP8NOd6SxbjwfbaS9iWb07BorN";
    AipOcr client = new AipOcr("16451907", "o77UXxGr55n0uEEd4UcQpbSG", "oT0tQEzP8NOd6SxbjwfbaS9iWb07BorN");

    public IdCardAuth() {
        this.client.setConnectionTimeoutInMillis(2000);
        this.client.setSocketTimeoutInMillis('\uea60');
    }

    public Back authBack(String image) throws FileNotFoundException {
        Back back = new Back();
        HashMap options = new HashMap();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        String idCardSide = "back";
        File file = new File(image);
        if(!file.exists()) {
            throw new FileNotFoundException(image);
        } else {
            JSONObject res = this.client.idcard(image, idCardSide, options);
            String image_status = res.getString("image_status");
            switch(image_status.hashCode()) {
            case -2124524108:
                if(image_status.equals("reversed_side")) {
                    back.setStatus("未摆正身份证");
                    return back;
                }
                break;
            case -1039745817:
                if(image_status.equals("normal")) {
                    JSONObject result = res.getJSONObject("words_result");
                    if(idCardSide.equals("back")) {
                        String auth = result.getJSONObject("签发机关").getString("words");
                        String start = result.getJSONObject("签发日期").getString("words");
                        String end = result.getJSONObject("失效日期").getString("words");
                        back.setAuth(auth);
                        back.setStart(start);
                        back.setEnd(end);
                        back.setStatus("success");
                    }

                    return back;
                }
                break;
            case -15443254:
                if(image_status.equals("blurred")) {
                    back.setStatus("身份证模糊，请重新拍摄");
                    return back;
                }
                break;
            case 272211986:
                if(image_status.equals("over_exposure")) {
                    back.setStatus("身份证反光,请调整灯光");
                    return back;
                }
                break;
            case 1044939869:
                if(image_status.equals("non_idcard")) {
                    back.setStatus("未检测到身份证");
                    return back;
                }
            }

            back.setStatus("识别失败");
            return back;
        }
    }

    public Front authFront(String image) throws FileNotFoundException {
        Front front = new Front();
        HashMap options = new HashMap();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        String idCardSide = "front";
        File file = new File(image);
        if(!file.exists()) {
            throw new FileNotFoundException(image);
        } else {
            JSONObject res = this.client.idcard(image, idCardSide, options);
            String image_status = res.getString("image_status");
            switch(image_status.hashCode()) {
            case -2124524108:
                if(image_status.equals("reversed_side")) {
                    front.setStatus("未摆正身份证");
                    return front;
                }
                break;
            case -1039745817:
                if(image_status.equals("normal")) {
                    JSONObject result = res.getJSONObject("words_result");
                    if(idCardSide.equals("front")) {
                        String gender = result.getJSONObject("性别").getString("words");
                        String name = result.getJSONObject("姓名").getString("words");
                        String address = result.getJSONObject("住址").getString("words");
                        String car_id = result.getJSONObject("公民身份号码").getString("words");
                        String day = result.getJSONObject("出生").getString("words");
                        String nation = result.getJSONObject("民族").getString("words");
                        front.setGender(gender);
                        front.setName(name);
                        front.setAddress(address);
                        front.setCar_id(car_id);
                        front.setDay(day);
                        front.setNation(nation);
                        front.setStatus("success");
                    }

                    return front;
                }
                break;
            case -15443254:
                if(image_status.equals("blurred")) {
                    front.setStatus("身份证模糊，请重新拍摄");
                    return front;
                }
                break;
            case 272211986:
                if(image_status.equals("over_exposure")) {
                    front.setStatus("身份证反光,请调整灯光");
                    return front;
                }
                break;
            case 1044939869:
                if(image_status.equals("non_idcard")) {
                    front.setStatus("未检测到身份证");
                    return front;
                }
            }

            front.setStatus("识别失败");
            return front;
        }
    }
}
