package com.haier.ranghood.annotationproxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haier.ranghood.annotation.BindView;
import com.haier.ranghood.annotationproxy.annotation.ReqParam;
import com.haier.ranghood.annotationproxy.annotation.ReqType;
import com.haier.ranghood.annotationproxy.annotation.ReqUrl;
import com.haier.ranghood.annotationproxy.api.IReqApi;
import com.haier.ranghood.api.LCJViewBinder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * 主要演示了注解的基本使用，模拟了retrofit的最基本功能，深刻掌握动态代理
 * <p>
 * 关于retrofit源码解析博客地址
 * http://mp.weixin.qq.com/s?__biz=MzI3MDE0NzYwNA==&mid=2651436401&idx=1&sn=d1cae937071824e023e955c17706f9bd&chksm=f1289c0ac65f151c4ce1abe876e940c662724251b734728f87047dfccabfb26502b8305cc044&mpshare=1&scene=23&srcid=11029upfUtAzzwoRnAdQhymu#rd
 * 动态代理就是在你想执行一个方法的前后插入自己想要的东西，比如此例子就是通过invoke的拦截首先解析了注解的信息然后进行了请求
 */


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_test)
    Button mButton;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LCJViewBinder.bind(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });
        testApi();
    }

    private void testApi() {
        IReqApi api = create(IReqApi.class);
        api.login("whoislcj", "123456");
    }


    public <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {// Annotation[]  methodAnnotations = method.getAnnotations();//拿到函数注解数组
                        ReqType reqType = method.getAnnotation(ReqType.class);
                        Log.e(TAG, "IReqApi---reqType->" + (reqType.reqType() == ReqType.ReqTypeEnum.POST ? "POST" : "OTHER"));
                        ReqUrl reqUrl = method.getAnnotation(ReqUrl.class);
                        Log.e(TAG, "IReqApi---reqUrl->" + reqUrl.reqUrl());
                        Type[] parameterTypes = method.getGenericParameterTypes();
                        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();//拿到参数注解
                        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                            Annotation[] annotations = parameterAnnotationsArray[i];
                            if (annotations != null) {
                                ReqParam reqParam = (ReqParam) annotations[0];
                                Log.e(TAG, "reqParam---reqParam->" + reqParam.value() + "==" + args[i]);
                            }
                        }
                        //下面就可以执行相应的网络请求获取结果 返回结果
                        String result = "";//这里模拟一个结果

                        return result;
                    }
                });
    }
}
