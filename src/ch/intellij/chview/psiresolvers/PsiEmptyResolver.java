package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;

public class PsiEmptyResolver extends AbstractResolver
{
    public PsiEmptyResolver(PsiElement element)
    {
    }

    @Override
    public String resolveAsString()
    {
        return "";
    }
}
