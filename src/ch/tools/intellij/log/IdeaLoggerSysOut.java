package ch.tools.intellij.log;

/**
 * Simple logger public API that should be used in this Plug-In for logging purpose.
 * BuildIn IDEA's logger doesn't provide any useful output consoles so it will be implemented as a custom logger Plugin.
 */
public class IdeaLoggerSysOut extends Logger
{
    protected void print(String msg, String level)
    {
        System.out.println(level + ": " + msg);
    }

    protected void print(String msg, Throwable thr, String level)
    {
        print(msg + " " + thr.getMessage(), level);
    }
}
