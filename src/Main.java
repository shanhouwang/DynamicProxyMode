import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 入口
 */
public class Main {

    public static void main(String[] args) {
        Service s = Main.create(Service.class, new MyService());
        s.hello();
        s.hi();
    }

    public static <T> T create(final Class<T> tClass, final Object target) {
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        long start = System.currentTimeMillis();
                        Object o = method.invoke(target, args);
                        System.out.println(method.getName() + " 方法消耗时间：" + (System.currentTimeMillis() - start) + "毫秒");
                        return o;
                    }
                });
    }

}
