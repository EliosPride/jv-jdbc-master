package mate.jdbc.util;

import mate.jdbc.lib.Injector;

public class InjectorUtils {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    private InjectorUtils() {
    }

    public static Object getInstance(Class<?> clazz) {
        return injector.getInstance(clazz);
    }
}
