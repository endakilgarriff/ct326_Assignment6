/*
    Name: Enda Kilgarriff
    Student ID: 17351606
 */

//Importing GUI libraries

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Lights extends JFrame {
    // Initialization of components. Accessible to private classes in Lights
    private JButton onOffButton, status;
    private JRadioButton timerButton;
    private JSlider brightnessSlider;
    private JComboBox<String> brightnessList;
    private JTextArea statusPrint;

    private Icon lightOn = new ImageIcon("lightBulbOn.png");
    private Icon lightOff = new ImageIcon("lightBulbOff.png");

    // Logic flags
    private static boolean lightOnOff, timerOnOff = false;

    // Constructor
    private Lights() {
        // Container Setup
        super("Assignment6 - Enda Kilgarriff - 17351606");
        Container mainMenu = getContentPane();

        // Select Layout type
        mainMenu.setLayout(new FlowLayout());

        // Event handler initialization
        ButtonHandler bHandler = new ButtonHandler();
        RadioButtonHandler rbHandler = new RadioButtonHandler();
        ComboBoxHandler cblHandler = new ComboBoxHandler();

        // Declare onOffButton, add event handler, add to mainMenu
        onOffButton = new JButton("Light Off", lightOff);
        onOffButton.addActionListener(bHandler);
        mainMenu.add(onOffButton);

        // Declare timerButton, add event handler, add to mainMenu
        timerButton = new JRadioButton("Timer");
        timerButton.addItemListener(rbHandler);
        mainMenu.add(timerButton);

        // Declare status button, add event handler, add to container
        status = new JButton("Status");
        status.addActionListener(bHandler);
        mainMenu.add(status);

        // Declare text area with dimensions, make it not editable, add to container
        statusPrint = new JTextArea(4,10);
        statusPrint.setEditable(false);
        mainMenu.add(statusPrint);

        //Declare brightnessList ComboBox, add array for drop down content,
        // Add event handling, add to container
        String[] brightnessLevel = {"Level 1","Level 2","Level 3","Level 4"};
        brightnessList = new JComboBox<>(brightnessLevel);
        brightnessList.setMaximumRowCount(4);
        brightnessList.addItemListener(cblHandler);
        mainMenu.add(brightnessList);

        // Declare brightnessSlider, add event handler(lambda function),
        // set elements of slider to be displayed, add to mainMenu
        brightnessSlider = new JSlider(1,4,1);
        brightnessSlider.addChangeListener(changeEvent -> {
            if (!brightnessSlider.getValueIsAdjusting()) {
                System.out.println("Brightness value: " + brightnessSlider.getValue());
                brightnessList.setSelectedIndex(brightnessSlider.getValue() - 1 );
            }
        });
        brightnessSlider.setMajorTickSpacing(1);
        brightnessSlider.setPaintTicks(true);
        brightnessSlider.setPaintLabels(true);
        mainMenu.add(brightnessSlider);

        setSize(370, 210);
        setVisible(true);

    }

    // Creates string to be displayed in status text area.
    private String getStatus(){
        String statusString = "";
        if(lightOnOff)  statusString += "Lights: on\n";
        else statusString += "Lights: off\n";

        if(timerOnOff) statusString += "Timer: active\n";
        else statusString += "Timer: Inactive\n";

        statusString += "Brightness Level: " + brightnessSlider.getValue();

        return statusString;
    }

    // Event handler for buttons.
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton btn = (JButton) actionEvent.getSource();
            // Logic depending on what button is pressed
            if (btn == onOffButton) {
                // Toggles lights on and off and sets icon to illustrate light status
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
            // Updates status text area when status button selected
            else if (btn == status) {
                System.out.println("Show status of light");
                statusPrint.setText(getStatus());
            }
        }
    }

    // Radio Button event handler
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

    private class ComboBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if(itemEvent.getStateChange() == ItemEvent.SELECTED){
                brightnessSlider.setValue(brightnessList.getSelectedIndex()+1);
            }
        }
    }

    public static void main(String[] args) {
        Lights lights = new Lights();
        lights.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
    }
}
