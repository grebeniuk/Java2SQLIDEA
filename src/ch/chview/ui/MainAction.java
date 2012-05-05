package ch.chview.ui;

import ch.ch.tools.intellij.log.Logger;
import ch.intellij.chview.psiresolvers.AbstractResolver;
import ch.intellij.chview.psiresolvers.PsiValueResolverFactory;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.ElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main starting point for the Plugin's logic
 */
public class MainAction extends AnAction
{
    private static final Logger log = Logger.getLogger(MainAction.class);
    private JTextPane textPane;

    public MainAction(String title, String descritpion, Icon icon)
    {
        super(title, descritpion, icon);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent)
    {
        runAction(anActionEvent);
    }

    protected void runAction(AnActionEvent anActionEvent)
    {
        List<IElementType> types = new ArrayList<IElementType>();

        types.add(ElementType.POLYADIC_EXPRESSION);
        types.add(ElementType.BINARY_EXPRESSION);

        // get opened file in the active editor
        PsiFile pf = DataKeys.PSI_FILE.getData(anActionEvent.getDataContext());
        Editor editor = DataKeys.EDITOR.getData(anActionEvent.getDataContext());

        // the editor is empty for example if you select some file in the Project tree:
        // focus will be moved from the tab to that tree. Try to use last stored editor.
        if (editor == null)
        {
            Messages.showMessageDialog("Please select some editor window.", "Java2SQL",
                    Messages.getInformationIcon());
            log.debug("Editor is Null. No further logic will be performed.");
            return;
        }

        // try to find a root element that can represent a String variable in the PSI tree
        PsiElement pe = pf.findElementAt(editor.getCaretModel().getOffset());
        pe = PsiTreeUtil.getParentOfType(pe, PsiPolyadicExpression.class, PsiBinaryExpression.class);

        if (pe == null)
        {
            Messages.showMessageDialog("Please select valid Java string expression", "Java2SQL",
                    Messages.getInformationIcon());
        }
        else
        {
            PsiElement[] elements = pe.getChildren();
            StringBuffer buf = new StringBuffer();
            
            for (PsiElement element : elements)
            {
                buf.append(AbstractResolver.getNormalizedString(
                        PsiValueResolverFactory.getResolver(element).resolveAsString()));
            }

            log.debug("Src: " + pe.getText());
            log.debug("Res: " + buf.toString());

            if (textPane != null)
            {
                textPane.setText(buf.toString());
            }
        }
    }

    public void setTextPane(JTextPane textPane)
    {
        this.textPane = textPane;
    }
}
