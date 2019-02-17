package com.xiaofeng.test.oom;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: 晓枫
 * @Date: 2019/1/30 12:22
 * @Description: Java方法区内存溢出：方法区存放Class的相关信息，类名、访问修饰符、常量池、字段描述、方法描述等。
 * 所以对于这个区域的测试是：运行时产生大量的类去填满方法区。
 *
 * 异常：OutofMemoryError: PermGen space
 */
public class JavaMethodAreaOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        while(true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
