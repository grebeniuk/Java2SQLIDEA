package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionList;

public class PsiExpressionListResolver extends AbstractResolver
{

    public PsiExpressionListResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        PsiExpression[] expressions = ((PsiExpressionList)element).getExpressions();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < expressions.length; i++)
        {
            PsiExpression expression = expressions[i];
            buf.append(PsiValueResolverFactory.getResolver(expression).resolveAsString());
        }

        return buf.toString();
    }
}
