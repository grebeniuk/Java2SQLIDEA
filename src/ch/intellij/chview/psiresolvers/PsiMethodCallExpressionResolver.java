package ch.intellij.chview.psiresolvers;

import ch.tools.intellij.log.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.util.PsiTreeUtil;

public class PsiMethodCallExpressionResolver extends AbstractResolver
{
    private static final Logger log = Logger.getLogger(PsiMethodCallExpressionResolver.class);

    public PsiMethodCallExpressionResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        log.debug("Element: " + element.getText());

        if (element.getText().contains("toString"))
        {
            element = PsiTreeUtil.getChildOfType(element, PsiReferenceExpression.class);
            element = PsiTreeUtil.getChildOfType(element, PsiReferenceExpression.class);

            return PsiValueResolverFactory.getResolver(element).resolveAsString();
        }

        return PsiValueResolverFactory.getResolver (((PsiMethodCallExpression)element).getArgumentList()).resolveAsString();
    }
}
