import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Lights extends JFrame {
    private JButton onOffButton, timerSettings, status;
    private JRadioButton timerButton;
    private JSlider brightnessSlider;
    private JComboBox brightnessList;

    private Icon lightOn = new ImageIcon("lightBulbOn.png");
    private Icon lightOff = new ImageIcon("lightBulbOff.png");

    //    private JPanel mainMenuTop, mainMenuMid, mainMenuBottom;
    private static boolean lightOnOff, timerOnOff = false;

    private Lights() {
        super("Lighting - Main Menu");
        Container mainMenu = getContentPane();

        mainMenu.setLayout(new FlowLayout());

        ButtonHandler bHandler = new ButtonHandler();
        RadioButtonHandler rbHandler = new RadioButtonHandler();

        status = new JButton("Status");
        mainMenu.add(status);
        status.addActionListener(bHandler);

        onOffButton = new JButton("Light Off", lightOff);
        onOffButton.addActionListener(bHandler);
        mainMenu.add(onOffButton);

        timerButton = new JRadioButton("Timer");
        timerButton.addItemListener(rbHandler);
        Component add = mainMenu.add(timerButton);


        brightnessSlider = new JSlider(1,4,1);
        brightnessSlider.addChangeListener(changeEvent -> {
            if (!brightnessSlider.getValueIsAdjusting()) {
                System.out.println("Brightness value: " + brightnessSlider.getValue());
            }
        });
        brightnessSlider.setMajorTickSpacing(1);
        brightnessSlider.setPaintTicks(true);
        brightnessSlider.setPaintLabels(true);
        mainMenu.add(brightnessSlider);
//        brightnessList.

        setSize(275, 200);
        setVisible(true);

    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton btn = (JButton) actionEvent.getSource();

            if (btn == onOffButton) {
                if (!lightOnOff) {
                    lightOnOff = true;
                    System.out.println("Lights On");
                    onOffButton.setText("Light On");
                    onOffButton.setIcon(lightOn);
                } else {
                    lightOnOff = false;
                    System.out.println("Lights Off");
                    onOffButton.setText("Light Off");
                    onOffButton.setIcon(lightOff);
                }
            }
//            else if (btn == timerSettings) {
//                System.out.println("Timer Settings opened");
//            }
            else if (btn == status) {
                System.out.println("Show status of light");
//                JFrame frame = new JFrame("Status Message");
//                JOptionPane.showMessageDialog(frame,"Status" );
            }
        }
    }

    private class RadioButtonHandler implements ItemListener {

        public void itemStateChanged(ItemEvent event) {
            if (event.getSource() == timerButton) {
                if (!timerOnOff) {
                    timerOnOff = true;
                    System.out.println("Timer On");
                } else {
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
