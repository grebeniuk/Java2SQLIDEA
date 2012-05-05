package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNewExpression;

public class PsiExpressionResolver extends AbstractResolver
{

    public PsiExpressionResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        return PsiValueResolverFactory.getResolver(((PsiNewExpression)element).getArgumentList()).resolveAsString();
    }

}
