/**
 GUIApp is the main app displaying the GUI

 @author Sulaiman Bakali Mueden
 @version 1
 */

import java.awt.*;
import java.awt.event.*;

public class GUIApp extends Frame{
    private static TextArea infoArea = new TextArea("Investment App", 5, 10, TextArea.SCROLLBARS_VERTICAL_ONLY);
    private static User user;

    public void print(String text) {
        infoArea.setText(text);
    }

    public static void setUserType(String type) {
        if (type.equals("Basic")) {
            user = new User(false);
        }
        else if (type.equals("Premium")) {
            user = new User(true);
        }
        else {
            System.out.print("Account type not set");
        }
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }


    public GUIApp() {
        setLayout(new GridLayout(3,1));

        // Title

        Panel titlePanel = new Panel();
        add(titlePanel);

        Label title = new Label("Investment App");
        titlePanel.add(title);

        //Buttons

        Panel buttonsPanel = new Panel();
        buttonsPanel.setLayout(new GridLayout(2,1));
        add(buttonsPanel);

        // Pannel 1 (Inner)

        Panel innerPanel = new Panel();
        buttonsPanel.add(innerPanel);

        Button b1 = new Button("Deposit");
        BtnActionListener listener = new BtnActionListener();
        b1.addActionListener(listener);

        Button b2 = new Button("Withdraw");
        b2.addActionListener(listener);

        Button b3 = new Button("Buy");
        b3.addActionListener(listener);

        Button b4 = new Button("Sell");
        b4.addActionListener(listener);

        innerPanel.add(b1);
        innerPanel.add(b2);
        innerPanel.add(b3);
        innerPanel.add(b4);

        // Panel 2 (Inner)

        Panel innerPanel2 = new Panel();
        buttonsPanel.add(innerPanel2);

        Button b5 = new Button("Portfolio");
        b5.addActionListener(listener);

        Button b6 = new Button("Account");
        b6.addActionListener(listener);

        Button b7 = new Button("Stocks");
        b7.addActionListener(listener);

        innerPanel2.add(b5);
        innerPanel2.add(b6);
        innerPanel2.add(b7);


        // TextArea

        add(infoArea);
        infoArea.setEditable(false);


        // Window

        addWindowListener(new WindowCloser() {});

        setTitle("GUIApp");
        setSize(350,350);

        setVisible(true);
    }

    /** Given whether the deposit or withdraw, the Prompt and the TextField where the user enters the amount,
     *  it performs checks to determine whether:
     *  - the user's input is a negative integer 
     *  - the user's input is not a valid integer
     *  @param action - String indicating which action to perform, such as withdraw or deposit
     *  @param prompt - The prompt window object aiding in performing the behaviour (eg. withdrawing)
     *  @param amountField - The TextField object the user will enter the amount they wish to deposit or withdraw 
     */
    public static String nonNegativeNumberTest(String action, Prompt prompt, TextField amountField) {
        try {
            int amount = Integer.parseInt((amountField.getText()));
            if (amount < 0) {
                prompt.dispose();
                return "Error. Please enter a non-negative integer";
            }
            else {
                if (action.equals("Deposit")) {
                    prompt.dispose();
                    user.deposit(amount);
                    return action + " was successful!";
                }
                else if (action.equals("Withdraw")) {
                    prompt.dispose();
                    if (user.withdraw(amount)) /* Withdrawing the amount from the User's account */
                        return action + " was successful!";
                    else
                        return action + " was unsuccessful!";
                }
                else {
                    prompt.dispose();
                    return action + "is an invalid action";
                }
            }
        }
        catch (NumberFormatException exception) {
            prompt.dispose();
            return "Error. Please enter an integer";
        }
    }

    /** The action listener that determines what action to perform
        based upon which button has been clicked in the GUIApp
        Buttons : Deposit, Withdraw, Portfolio, Account, Buy, Sell and Stocks
     */
    private class BtnActionListener implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            // Deposit Button
            if (evt.getActionCommand().equals("Deposit")) {
                Prompt prompt = new Prompt();
                TextField amountField = prompt.createInputInteger();
                Button submitButton = prompt.addSubmit();

                submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String response = nonNegativeNumberTest(evt.getActionCommand(), prompt, amountField);
                        print(response);
                    }
                });
            }

            // Withdraw Button
            else if (evt.getActionCommand().equals("Withdraw")) {
                Prompt prompt = new Prompt();
                TextField amountField = prompt.createInputInteger();
                Button submitButton = prompt.addSubmit();

                submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String response = nonNegativeNumberTest(evt.getActionCommand(), prompt, amountField);
                        print(response);
                    }
                });
            }

            // Portfolio Button
            else if (evt.getActionCommand().equals("Portfolio")) {
                print("Portfolio\n\n" +
                        user.getPortfolio().toString());
            }

            // Account Button
            else if (evt.getActionCommand().equals("Account")) {
                print("Account\n\n" +
                        user.getAccountDescription() +
                        "\n\nYour current balance is £" + user.getBalance());
            }

            // Buy Button
            else if (evt.getActionCommand().equals("Buy")){
                Prompt prompt = new Prompt();

                // List (containing all the stocks)
                Object[] stockArray = Market.getMarketArray();
                List stockList = prompt.createList(stockArray);
                Button submitButton = prompt.addSubmit();

                submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Retrieving the stock from the Market stock list and trying to add it
                        try {
                            int stockIndex = stockList.getSelectedIndex();
                            Stock stock = (Stock) stockArray[stockIndex];
                            if (user.addStock(stock)) {
                                print(user.getLastReciept().toString());
                            }
                            else {
                                print("Insufficient Funds!");
                            }
                            prompt.dispose();
                        }
                        catch (IndexOutOfBoundsException exception) {
                            prompt.dispose();
                            print("Error. Stock not selected");
                            //throw (new ArrayIndexOutOfBoundsException("Stock not selected"));
                        }
                    }
                });
            }

            // Sell Button
            else if (evt.getActionCommand().equals("Sell")){
                Prompt prompt = new Prompt();

                // List (containing all the stocks in the user's portfolio)
                Portfolio portfolio = user.getPortfolio();
                Object[] portfolioArray = portfolio.getPortfolioArray();
                List portfolioList = prompt.createList(portfolioArray);
                Button submitButton = prompt.addSubmit();

                submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Retrieving the stock object from their portfolio and trying to remove it
                        try {
                            int stockIndex = portfolioList.getSelectedIndex();
                            Stock stock = (Stock) portfolioArray[stockIndex];
                            user.removeStock(stock);
                            print(user.getLastReciept().toString());
                            prompt.dispose();
                        }
                        catch (ArrayIndexOutOfBoundsException exception) {
                            prompt.dispose();
                            print("Error. Stock not selected");
                            //throw (new ArrayIndexOutOfBoundsException("Stock not selected"));
                        }
                    }
                });
            }

            // Stocks Button
            else {
                // Print of all the stocks
                Object[] stockArray = Market.getMarketArray();
                String stockListText = "Stocks\n\n";

                for (int i=0; i< stockArray.length; i++) {
                    stockListText += stockArray[i].toString();
                }

                print(stockListText);
            }
        }
    }
}
