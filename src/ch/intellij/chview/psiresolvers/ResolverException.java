package ch.intellij.chview.psiresolvers;

public class ResolverException extends Exception
{
    public ResolverException()
    {
        super();
    }

    public ResolverException(String msg)
    {
        super(msg);
    }

    public ResolverException(String msg, Throwable t)
    {
        super(msg, t);
    }
}