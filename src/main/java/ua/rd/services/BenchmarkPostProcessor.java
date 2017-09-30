package ua.rd.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;
import ua.rd.annotations.Benchmark;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class BenchmarkPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class<?> thisClass = o.getClass();
        Class<?> superClass = thisClass.getSuperclass();
        if(Arrays.stream(thisClass.getDeclaredMethods())
                .anyMatch(m -> m.isAnnotationPresent(Benchmark.class) &&
                        m.getAnnotation(Benchmark.class).enabled()) ||
                Arrays.stream(superClass.getDeclaredMethods())
                        .anyMatch(m -> m.isAnnotationPresent(Benchmark.class) &&
                                m.getAnnotation(Benchmark.class).enabled())) {
            return createBenchmarkProxy(o);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        //System.out.println("+++After Init");
        return o;
    }

    private Object createBenchmarkProxy(Object bean) {
        Class<?> beanType = bean.getClass();
        return Proxy.newProxyInstance(beanType.getClassLoader(),
                ClassUtils.getAllInterfaces(bean),
                (proxy, method, args) -> {
                    System.out.println(method.getName());
                    System.out.println(beanType);
                    System.out.println(Arrays.toString(beanType.getDeclaredMethods()));
                    Method beanMethod = beanType.getDeclaredMethod(method.getName(),
                            method.getParameterTypes());
                    if (beanMethod.isAnnotationPresent(Benchmark.class) &&
                            beanMethod.getAnnotation(Benchmark.class).enabled()) {
                        long start = System.nanoTime();
                        Object objectToReturn = method.invoke(bean, args);
                        long finish = System.nanoTime();
                        System.out.println(finish - start);
                        return objectToReturn;
                    } else {
                        return method.invoke(bean, args);
                    }
                });
    }
}
