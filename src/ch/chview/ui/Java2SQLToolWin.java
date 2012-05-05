package ch.chview.ui;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Main control.
 */
public class Java2SQLToolWin implements ToolWindowFactory
{
    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow)
    {
        toolWindow.setIcon(IconLoader.getIcon(Icons.SQL));
        Component component = toolWindow.getComponent();

        JTextPane sqlText = new JTextPane();
        sqlText.setBackground(new Color(255, 248, 220));
        sqlText.setEditable(false);

        MainAction action = new MainAction("Java2SQL", "Java2SQL", IconLoader.getIcon(Icons.SQL));
        action.setTextPane(sqlText);

        DefaultActionGroup group = new DefaultActionGroup();
        group.add(action);
        JComponent toolBar = ActionManager.getInstance().createActionToolbar("Java2SQL", group, true).getComponent();

        JScrollPane scrollPane = new JScrollPane(sqlText);

        FormLayout layout = new FormLayout(
                "pref, pref:grow",
                "fill:pref:grow");
        CellConstraints cc = new CellConstraints();

        JPanel p = new JPanel(layout);
        p.add(toolBar, cc.xy (1, 1));
        p.add(scrollPane, cc.xy (2, 1));

        component.getParent().add(p);
    }
}
