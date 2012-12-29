package ch.chview.ui;

import ch.tools.intellij.log.Logger;
import ch.tools.intellij.psi.PsiTools;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import javax.swing.*;

/**
 * Main entering point for the Plugin's logic
 */
public class MainAction extends AnAction
{
    private static final Logger log = Logger.getLogger(MainAction.class);
    private JTextArea textPane;

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
        String sql;
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
        pe = PsiTreeUtil.getParentOfType(pe,
                PsiPolyadicExpression.class,
                PsiBinaryExpression.class,
                PsiMethodCallExpression.class);

        if (pe == null)
        {
            Messages.showMessageDialog("Please select valid Java string expression", "Java2SQL",
                    Messages.getInformationIcon());
            return;
        }

        sql = PsiTools.psiElementToString(pe);

        if (textPane != null)
        {
            setText(sql);
        }

    }

    private void setText(String str)
    {
        final String buf = str;
        // using the Swing Thread.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                textPane.setText(buf);
                textPane.repaint();
            }
        });

    }

    public void setTextPane(JTextArea textPane)
    {
        this.textPane = textPane;

    }
}
