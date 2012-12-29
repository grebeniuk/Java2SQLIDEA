package ch.tools.intellij.psi;

import ch.intellij.chview.psiresolvers.AbstractResolver;
import ch.intellij.chview.psiresolvers.PsiValueResolverFactory;
import ch.tools.intellij.log.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 */
public class PsiTools
{
    private static final Logger log = Logger.getLogger(PsiTools.class);

     /**
     * Depends on PsiElement type iterates over PsiTree and concatenates all found String values.
     *
     * @param pe Given PsiElement
     * @return Concatenated string values from PsiTree.
     */
    public static String psiElementToString(PsiElement pe)
    {

    String str;
    List<String> strings = new LinkedList<String>();

    // to support StringBuilder.append("str1").append("str2").toString();
    if (pe instanceof PsiMethodCallExpression)
    {
        pe = PsiTreeUtil.getTopmostParentOfType(pe, PsiMethodCallExpression.class);

        while (pe instanceof PsiMethodCallExpression)
        {
            log.debug("PE: " + pe);

            strings.add(resolveNode(pe, PsiExpressionList.class));
            pe = PsiTreeUtil.findChildOfType(pe, PsiMethodCallExpression.class);
        }

        str = getStringFromReversedList(strings);
    }
    else
    {
        str = resolveNode(pe, null);
    }

    return str;
    }

    /**
     * Concatenates all string in the given list from the last element to the first one.
     * @param strings list with Strings
     * @return Concatenated string.
     */
    public static String getStringFromReversedList(List<String> strings)
    {
        StringBuilder buf = new StringBuilder();
        ListIterator li = strings.listIterator(strings.size());

        while (li.hasPrevious())
        {
            buf.append(li.previous());
        }

        return buf.toString();
    }

    /**
     * Uses PsiValueResolverFactory to get String value from given Psi node from it's child of the tree.
     * @param pe Node.
     * @param clazz Can be null. In clazz is NULL all nodes will be parsed,
     *              otherwise only child of provided clazz will be processed.
     * @return String representation of the node.
     */
    public static String resolveNode(PsiElement pe, Class clazz)
    {
        String resolvedText;
        PsiElement[] elements;
        StringBuffer buf = new StringBuffer();

        if (clazz != null)
        {
            elements = PsiTreeUtil.getChildrenOfType(pe, clazz);
        }
        else
        {
            elements = pe.getChildren();
        }


        for (PsiElement element : elements)
        {
            resolvedText = AbstractResolver.getNormalizedString(
                    PsiValueResolverFactory.getResolver(element).resolveAsString());

            log.debug("Resolved text: " + resolvedText);

            buf.append(resolvedText);
        }

        log.debug("Src: " + pe.getText());
        log.debug("Res: " + buf.toString());

        return buf.toString();
    }

}
