package com.haier.ranghood.annotationproxy.api;

import com.haier.ranghood.annotationproxy.annotation.ReqParam;
import com.haier.ranghood.annotationproxy.annotation.ReqType;
import com.haier.ranghood.annotationproxy.annotation.ReqUrl;

/**
 * Created by Harry.Kong.
 * Time 2017/11/2.
 * Email kongpengcheng@aliyun.com.
 * Description:
 */
public interface IReqApi {

    @ReqType(reqType = ReqType.ReqTypeEnum.POST)//声明采用post请求
    @ReqUrl(reqUrl = "www.xxx.com/openApi/login")//请求Url地址
    String login(@ReqParam("userId") String userId, @ReqParam("pwd") String pwd);//参数用户名 密码

}