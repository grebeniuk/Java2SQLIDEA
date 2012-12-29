package ch.intellij.chview.psiresolvers;

import ch.tools.intellij.log.Logger;
import com.intellij.psi.*;
import com.intellij.psi.impl.compiled.ClsMethodImpl;

public class PsiValueResolverFactory
{
    private static final Logger log = Logger.getLogger(PsiValueResolverFactory.class);

    /**
     * Determinate PsiElement's type and returns appropriate resolver for it.
     *
     * @param element PsiElement instance.
     * @return Node values resolver.
     */
    public static IResolver getResolver(PsiElement element)
    {
        if (element == null)
        {
            log.debug("Element is NULL, return PsiEmptyResolver.");
            return new PsiEmptyResolver(element);
        }

        log.debug("Element: " + element.getText() +
                ", type = " + element.getNode().getElementType() +
                ", class = " + element.getClass());

        if (element instanceof PsiReferenceExpression)
        {
            return new ReferenceExpressionResolver(element);
        }
        else if (element instanceof PsiWhiteSpace)
        {
            return new PsiEmptyResolver(element);
        }
        else if (element instanceof PsiJavaToken)
        {
            return new PsiEmptyResolver(element);
        }
        else if (element instanceof PsiNewExpression)
        {
            return new PsiExpressionResolver(element);
        }
        else if (element instanceof PsiMethodCallExpression)
        {
            return new PsiMethodCallExpressionResolver(element);
        }
        else if (element instanceof PsiExpressionList)
        {
            return new PsiExpressionListResolver(element);
        }
        else if (element instanceof PsiField)
        {
            return new FieldResolver(element);
        }
        else if (element instanceof ClsMethodImpl)
        {
            return new PsiEmptyResolver(element);
        }
        else
        {
            log.debug("No specific resolver found, default resolver will be used.");
            return new GenericResolver(element);
        }
    }
}
