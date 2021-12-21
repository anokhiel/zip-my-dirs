package zipper.action;

import zipper.Model;
import zipper.UI;
import zipper.Zipper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonClickListener implements ActionListener {
    private Thread exe;
    @Override
    public void actionPerformed(ActionEvent e) {
        Model model= UI.model;
        if(model.getSource()==null || model.getTarget()==null){
            UI.statusLabel.setText("Choose source and target!");
            return;
        }
        if(!UI.disableButton){
            UI.disableButton=true;
            UI.warn();
            exe=new Thread(new Zipper(model),"Zipper");
                    exe.start();
        }else{
            exe.interrupt();
            UI.disableButton=false;
            UI.resetButton();
            UI.statusLabel.setText("");
        }

    }
}
