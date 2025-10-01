/**
 Menu is the GUI's start menu allowing the user to select their account

 @author Sulaiman Bakali Mueden
 @version 1
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Menu extends Frame {
    public Menu() {
        setLayout(new GridLayout(4, 1));

        addWindowListener(new WindowCloser() {});



        Panel infoPanel = new Panel();

        // Label (Information)

        Label info = new Label("Please select the account type you would like: ");

        infoPanel.add(info);


        Panel optionPanel = new Panel(new FlowLayout());

        // Radio Choice (Account Types)

        Panel innerOptionPanel = new Panel(new GridLayout(4,1));

        CheckboxGroup accountTypes = new CheckboxGroup();

        Checkbox basic = new Checkbox("Basic Account", accountTypes, true);
        Checkbox premium = new Checkbox("Premium Account (Overdraw £100, 5% Interest)", accountTypes, false);
        Checkbox load = new Checkbox(("Load stored User (if any)"), accountTypes, false);

        innerOptionPanel.add(basic);
        innerOptionPanel.add(premium);
        innerOptionPanel.add(load);
        optionPanel.add(innerOptionPanel);


        // File IO Error Label
        Label errorLabel = new Label("Unable to load user", Label.CENTER);
        errorLabel.setVisible(false);
        errorLabel.setForeground(Color.red);




        Button submitButton = new Button("Submit");


        /** Determine which Radio Choice was Selected.
         *
         *  Based upon which Account Type they selected,
         *  either set the User's account field to store a
         *  - BasicAccount object,
         *  - PremiumAccount object,
         *  - or load the previous user and set the User's account
         *   to that object (whether Basic or Premium)
         */
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Checkbox selected = accountTypes.getSelectedCheckbox();
                String label = selected.getLabel();

                if (label.equals("Basic Account")) {
                    GUIApp.setUserType("Basic");
                    GUIApp app = new GUIApp();
                    dispose();
                }

                else if (label.equals(("Premium Account (Overdraw £100, 5% Interest)"))) {
                    GUIApp.setUserType("Premium");
                    GUIApp app = new GUIApp();
                    dispose();
                }

                else {
                    try {
                        String filename = "userStorage.txt";
                        FileInputStream inputStream = new FileInputStream(filename);
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        User user = (User) objectInputStream.readObject();
                        GUIApp.setUser(user);
                        objectInputStream.close();
                        inputStream.close();

                        GUIApp app = new GUIApp();
                        dispose();
                    }
                    catch (IOException | ClassNotFoundException exception) {
                        errorLabel.setVisible(true);
                        load.setEnabled(false);
                    }
                }
            }
        });


        add(infoPanel);
        add(optionPanel);
        add(errorLabel);
        add(submitButton);

        setTitle("Menu");
        setSize(350, 350);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException{
        Menu menu = new Menu();

        // Add stocks
        Stock apple = new Stock("Apple", 100);
        Stock microsoft = new Stock("Microsoft", 90);
        Stock google = new Stock("Google", 80);

	    Market.add(apple);
	    Market.add(microsoft);
	    Market.add(google);


        /** Extra Feature
         *  Changing the price of every stock by either -1, 0 or 1 every 5 seconds
         *  from when Menu has been compiled and run
         */
        Timer stockFlucuation = new Timer();
        stockFlucuation.schedule(new TimerTask() {
            public void run() {
                Random random = new Random();
                Object[] marketArray = Market.getMarketArray();
                for (int i=0; i< marketArray.length; i++) {
                    int priceFlucation = random.nextInt(3) - 1;
                    Stock nextStock = (Stock) marketArray[i];
                    nextStock.changePrice(priceFlucation);
                }
            }
        }, 0, 5000);
    }
}
