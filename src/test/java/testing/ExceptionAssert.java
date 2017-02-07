package testing;

import java.lang.reflect.Type;

public final class ExceptionAssert
{
    private ExceptionAssert()
    {
    }

    public static void assertThrows(final Type exceptionType, final Runnable runnable)
    {
        getException(exceptionType, runnable);
    }

    public static <T> T getException(final Type exceptionType, final Runnable runnable)
    {
        try
        {
            runnable.run();
            throw new ExceptionAssertFailed(String.format("Expected exception of type <%s>, but no exception was thrown.", exceptionType.getTypeName()));
        }
        catch (Exception ex)
        {
            if (!(ex.getClass() == exceptionType) && !(ex.getClass().getSuperclass() == exceptionType))
                throw new ExceptionAssertFailed(String.format("Expected exception type: <%s>. Actual: <%s>", exceptionType.getTypeName(), ex.getClass().getTypeName()));
            return (T)ex;
        }
    }

    public static void assertDoesNotThrow(final Runnable runnable)
    {
        try
        {
            runnable.run();
        }
        catch (Exception ex)
        {
            throw new ExceptionAssertFailed(String.format("Exception was thrown of type: <%s>.", ex.getClass().getTypeName()));
        }
    }

    public static final class ExceptionAssertFailed extends RuntimeException
    {
        public ExceptionAssertFailed(final String messsage)
        {
            super(messsage);
        }
    }
}