package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.apache.commons.lang.StringUtils;

public class ReferenceExpressionResolver extends AbstractResolver implements IResolver
{
    public ReferenceExpressionResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        PsiReference ref = element.getReference();

        String res = PsiValueResolverFactory.getResolver(ref.resolve()).resolveAsString();

        if(StringUtils.isEmpty(res))
        {
            res = ResolverConstants.UNKNOW_VAL;
        }

        return getFormattedVariable(res, element);
    }
}
