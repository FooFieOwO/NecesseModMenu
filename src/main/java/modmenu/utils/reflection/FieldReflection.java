package modmenu.utils.reflection;

import java.lang.reflect.Field;

public final class FieldReflection {

    public static Field getField(final Class<?> clazz, final String name, final boolean inSuperclass) throws NoSuchFieldException {
        return inSuperclass ? clazz.getSuperclass().getDeclaredField(name) : clazz.getDeclaredField(name);
    }

    public static void setFieldValue(final Class<?> clazz, final Object clazzInstance, final String name, final boolean inSuperclass, final boolean value) {
        try {
            final Field field = getField(clazz, name, inSuperclass);
            field.setAccessible(true);
            field.set(clazzInstance, value);
            field.setAccessible(false);
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getFieldValue(final Class<?> object, final Object clazzInstance, final String name, final boolean inSuperclass) {
        try {
            final Field field = getField(object, name, inSuperclass);
            field.setAccessible(true);
            final Object value = field.get(clazzInstance);
            field.setAccessible(false);
            return value;
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
