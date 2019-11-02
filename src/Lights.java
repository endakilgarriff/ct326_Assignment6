import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Lights extends JFrame{
    private JButton onOffButton,  timerSettings;
    private JRadioButton timerButton;
    private JSlider brightness;
//    private JPanel mainMenuTop, mainMenuMid, mainMenuBottom;
    private static boolean  lightOnOff,timerOnOff = false;

    private Lights() {
        super("Lighting - Main Menu") ;
        Container mainMenu = getContentPane();

        mainMenu.setLayout(new FlowLayout());

        ButtonHandler bHandler = new ButtonHandler();
        RadioButtonHandler rbHandler = new RadioButtonHandler();

        onOffButton = new JButton("On/Off");
        mainMenu.add(onOffButton);
        onOffButton.addActionListener(bHandler);

        timerButton = new JRadioButton("Timer");
        mainMenu.add(timerButton);
        timerButton.addItemListener(rbHandler);

        Icon cog = new ImageIcon("cog.png");
        timerSettings = new JButton("", cog);
        mainMenu.add(timerSettings);
        timerSettings.addActionListener(bHandler);

        brightness = new JSlider(SwingConstants.HORIZONTAL, 1,4,1 );
        brightness.setMajorTickSpacing(1);
        brightness.setPaintTicks(true);
        brightness.setPaintLabels(true);
        mainMenu.add(brightness);
        brightness.addChangeListener(changeEvent -> {
            if(!brightness.getValueIsAdjusting()){
                System.out.println("Brightness value: " + brightness.getValue());
            }
        });

        setSize( 275,200);
        setVisible(true);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton btn = (JButton) actionEvent.getSource();


            if (btn == onOffButton) {
                if(!lightOnOff){
                    lightOnOff = true;
                    System.out.println("Lights On");
                }
                else {
                    lightOnOff = false;
                    System.out.println("Lights Off");
                }
            }
            else if (btn == timerSettings) {
                System.out.println("Timer Settings opened");
            }
        }
    }

    private class RadioButtonHandler implements ItemListener{

        public void itemStateChanged(ItemEvent event){
            if(event.getSource() == timerButton){
                if(!timerOnOff){
                    timerOnOff = true;
                    System.out.println("Timer On");
                }
                else {
                    timerOnOff = false;
                    System.out.println("Timer Off");
                }
            }
        }
    }
    public static void main(String[] args) {
     Lights lights = new Lights();
     lights.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);
    }
}
