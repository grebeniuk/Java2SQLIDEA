package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;

public class FieldResolver extends AbstractResolver
{
    public FieldResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        return PsiValueResolverFactory.getResolver(((PsiField)element).getInitializer()).resolveAsString();
    }
}
