package ch.tools.intellij.log;

/**
 * Abstratct logger.
 */
public abstract class Logger
{
    private static Logger log;
    private static String DEBUG = "DUBUG";
    private static String INFO = "INFO";
    private static String ERROR = "ERROR";
    private static String TRACE = "TRACE";
    private static String WARN = "WARN";

    /**
     * Get log subsystem.
     *
     * @param clazz
     * @return
     */
    public static Logger getLogger(Object clazz)
    {
        if (log == null)
        {
            log = new IdeaLoggerSysOut();
        }

        return log;
    }

    public void debug(String msg)
    {
        print(msg, DEBUG);
    }

    public void debug(String msg, Throwable thr)
    {
        print(msg, thr, DEBUG);
    }

    public void warn(String msg)
    {
        print(msg, WARN);
    }

    public void warn(String msg, Throwable thr)
    {
        print(msg, thr, WARN);
    }

    public void error(String msg)
    {
        print(msg, ERROR);
    }

    public void error(String msg, Throwable thr)
    {
        print(msg, ERROR);
    }

    public void info(String msg, Throwable thr)
    {
        print(msg, thr, INFO);
    }

    public void info(String msg)
    {
        print(msg, INFO);
    }

    public void trace(String msg)
    {
        print(msg, TRACE);
    }

    public void trace(String msg, Throwable thr)
    {
        print(msg, thr, TRACE);
    }

    protected abstract void print(String msg, Throwable thr, String debug);
    protected abstract void print(String msg, String debug);
}
