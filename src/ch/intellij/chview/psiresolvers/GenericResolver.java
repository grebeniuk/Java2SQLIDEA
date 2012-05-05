package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;

public class GenericResolver extends AbstractResolver
{
    public GenericResolver(PsiElement element)
    {
        super(element);
    }

    @Override
    public String resolveAsString()
    {
        return element.getText();
    }
}
