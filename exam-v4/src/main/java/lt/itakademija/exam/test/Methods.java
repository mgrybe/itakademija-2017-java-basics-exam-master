package lt.itakademija.exam.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mariusg on 2017.03.18.
 */
final class Methods {

    private Methods() {
        throw new UnsupportedOperationException();
    }

    public static final class MethodBuilder {
        private final MethodProducer methodProducer;

        private Class<? extends Exception> exceptionType;

        public MethodBuilder(SimpleMethodProducer methodProducer) {
            this.methodProducer = methodProducer;
        }

        public MethodBuilder throwing(Class<? extends Exception> exceptionType) {
            this.exceptionType = exceptionType;
            return this;
        }

        public <T> ThrowsMatcher<T> invokedWithArgs(ArgsProducer argsProducer) {
            return new ThrowsMatcher<>(methodProducer, argsProducer, exceptionType);
        }
    }

    public static MethodBuilder hasMethod(final String methodName, Class<?>... perameterTypes) {
        return new MethodBuilder(new SimpleMethodProducer(methodName, perameterTypes));
    }

    public static ArgsProducer permuteWithNull(Object... args) {
        return new ValuePermutationArgsProducer(null, args);
    }

    public static <T> T[] with(T... args) {
        return args;
    }

    private static String toMethodSignature(final Method method, Object[] args) {
        String[] stringArgs = new String[args.length];

        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                stringArgs[i] = arg != null ? arg.getClass().getSimpleName() : "null";
            }
        }

        return toMethodSignature(method.getName(), stringArgs);
    }

    private static String toMethodSignature(final String methodName, Class<?>... parameterTypes) {
        String[] args = new String[parameterTypes.length];

        if (parameterTypes != null && parameterTypes.length > 0) {
            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = parameterTypes[i].getSimpleName();
            }
        }

        return toMethodSignature(methodName, args);
    }

    private static String toMethodSignature(final String methodName, String[] args) {
        final StringBuilder sb = new StringBuilder();

        sb.append(methodName).append("(");
        if (args != null && args.length > 0) {
            sb.append(args[0]);

            for (int i = 1; i < args.length; i++) {
                sb.append(", ").append(args[i]);
            }
        }
        sb.append(")");

        return sb.toString();
    }

    private static final class ThrowsMatcher<T> extends BaseMatcher<T> {

        private final MethodProducer methodProducer;
        private final Class<? extends Exception> exceptionType;
        private final ArgsProducer argsProducer;

        private Object[] lastInvocationArgs;
        private Method method;

        public ThrowsMatcher(MethodProducer methodProducer,
                             ArgsProducer argsProducer,
                             Class<? extends Exception> exceptionType) {
            this.methodProducer = methodProducer;
            this.argsProducer = argsProducer;
            this.exceptionType = exceptionType;
        }

        @Override
        public boolean matches(Object item) {
            method = methodProducer.produce(item);
            if (method == null) {
                return false;
            }

            if (argsProducer instanceof Iterable) {
                boolean ok = false;
                for (Object args : (Iterable) argsProducer) {
                    lastInvocationArgs = (Object[]) args;

                    ok = match(item, method, lastInvocationArgs);
                    if (!ok) {
                        return false;
                    }
                }
                return ok;
            } else {
                lastInvocationArgs = argsProducer.produce();
                return match(item, method, lastInvocationArgs);
            }
        }

        private boolean match(Object item, Method method, Object[] args) {
            try {
                method.invoke(item, args);

                return false;
            } catch (InvocationTargetException e) {
                if (!exceptionType.isAssignableFrom(e.getTargetException().getClass())) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("throws exception ")
                    .appendText(exceptionType.getCanonicalName());
        }

        @Override
        public void describeMismatch(Object item, Description description) {
            description.appendText("exception was not thrown for invocation: ")
                    .appendText(item.getClass().getCanonicalName())
                    .appendText("#")
                    .appendText(toMethodSignature(method, lastInvocationArgs));
        }
    }

    interface ArgsProducer {
        Object[] produce();
    }

    interface MethodProducer {
        Method produce(Object object);
    }

    private static final class SimpleMethodProducer implements MethodProducer {

        private final String methodName;

        private final Class<?>[] perameterTypes;

        public SimpleMethodProducer(String methodName, Class<?>[] perameterTypes) {
            this.methodName = methodName;
            this.perameterTypes = perameterTypes;
        }

        @Override
        public Method produce(Object object) {
            try {
                return object.getClass().getMethod(methodName, perameterTypes);
            } catch (NoSuchMethodException e) {
                return null;
            }
        }
    }

    private static final class SimpleArgsProducer implements ArgsProducer {

        private final Object[] args;

        public SimpleArgsProducer(Object[] args) {
            this.args = args;
        }

        @Override
        public Object[] produce() {
            return Arrays.copyOfRange(args, 0, args.length);
        }
    }

    static final class ValuePermutationArgsProducer implements ArgsProducer, Iterable<Object[]> {

        private final Object value;

        private final Object[] args;

        public ValuePermutationArgsProducer(Object value, Object... args) {
            this.value = value;
            this.args = args;
        }

        @Override
        public Object[] produce() {
            List<Object[]> result = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                Object[] copiedArgs = Arrays.copyOfRange(args, 0, args.length);
                copiedArgs[i] = value;
                result.add(copiedArgs);
            }
            return result.toArray(new Object[0]);
        }

        @Override
        public Iterator<Object[]> iterator() {
            Object[] args = produce();

            List<Object[]> argsList = new ArrayList<>(args.length);
            for (Object a : args) {
                argsList.add((Object[]) a);
            }

            return argsList.iterator();
        }
    }
}
