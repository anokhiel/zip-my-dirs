package zipper.action;

import zipper.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TargetButtonClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            UI.model.setTarget(fc.getSelectedFile().toPath());
            UI.fileLabel1.setText(UI.model.getTarget().toString());
        }
    }
}
