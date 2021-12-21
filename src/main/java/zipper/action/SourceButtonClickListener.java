package zipper.action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import zipper.UI;

public class SourceButtonClickListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            UI.model.setSource(fc.getSelectedFile().toPath());
            UI.fileLabel.setText(UI.model.getSource().toString());
        }
    }
}

