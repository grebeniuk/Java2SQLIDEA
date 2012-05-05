package ch.intellij.chview.psiresolvers;

import com.intellij.psi.PsiElement;

public interface IResolver
{
    /**
     * String representation of the PsiElement element.
     *
     * @return Could return Null if resolver can't resolve value.
     */
    String resolveAsString();


    /**
     * Returns formatted string for values displaying.
     * For example add variable name as a comment after it's value
     *
     * @return String
     * @param res
     * @param element
     */
    String getFormattedVariable(String res, PsiElement element);
}