package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.util.PsiTreeUtil;

public class PsiMethodCallExpressionResolver extends AbstractResolver
{
    public PsiMethodCallExpressionResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        if (element.getText().contains("toString"))
        {
            element = PsiTreeUtil.getChildOfType(element, PsiReferenceExpression.class);
            element = PsiTreeUtil.getChildOfType(element, PsiReferenceExpression.class);

            return PsiValueResolverFactory.getResolver(element).resolveAsString();
        }

        return PsiValueResolverFactory.getResolver (((PsiMethodCallExpression)element).getArgumentList()).resolveAsString();
    }
}
