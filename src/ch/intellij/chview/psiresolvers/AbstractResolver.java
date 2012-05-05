package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;

/**
 * Main parent class for all Resolvers
 */
public abstract class AbstractResolver implements IResolver
{
    protected PsiElement element;

    public AbstractResolver(PsiElement element)
    {
        this.element = element;
    }

    protected AbstractResolver()
    {
    }

    public abstract String resolveAsString();

    /**
     * Wrap string into java comment strings.
     * @param str Source string.
     * @return Wrapped string.
     */
    protected String wrapIntoComment(String str)
    {
        return "/*" + str + "*/";
    }

    /**
     * Format value for pretty output.
     * @return Formatted string.
     * @param value Value of a variable
     * @param element Variable Psi element
     */
    public String getFormattedVariable(String value, PsiElement element)
    {
        value = value == null ? ResolverConstants.UNKNOW_VAL : value;

        return value + " " + wrapIntoComment(this.element.getText());
    }

    /**
     * Removes Java strings specific symbols.
     * @param str Source java string
     * @return SQL
     */
    public static String getNormalizedString(String str)
    {
        str = str.replace("\"", "");
        str = str.replace("\\n", "\n");

        return str;
    }
}
