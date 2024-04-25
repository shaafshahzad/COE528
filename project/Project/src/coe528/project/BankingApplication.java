package coe528.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.scene.text.Text;
import java.nio.file.Path;

public class BankingApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account Application Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        grid.add(btn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            try {
                if (authenticate(username, password)) {
                    if ("admin".equals(username)) {
                        showManagerScreen(primaryStage);
                    } else {
                        File file = new File(username + ".txt");
                        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
                        if (lines.size() >= 3 && lines.get(1).equals(password)) {
                            double balance = Double.parseDouble(lines.get(2));
                            Customer customer = new Customer(username, password, balance);
                            showCustomerScreen(primaryStage, customer, username);
                        } else {
                            actiontarget.setFill(javafx.scene.paint.Color.RED);
                            actiontarget.setText("Login failed");
                        }
                    }
                } else {
                    actiontarget.setFill(javafx.scene.paint.Color.RED);
                    actiontarget.setText("Login failed");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private boolean authenticate(String username, String password) throws Exception {
        File file = new File(username + ".txt");
        if (file.exists()) {
            List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
            return lines.size() >= 2 && lines.get(0).equals(username) && lines.get(1).equals(password);
        }
        return false;
    }

    private void showManagerScreen(Stage primaryStage) {
        primaryStage.setTitle("Manager Dashboard");

        Manager manager = new Manager();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        // add user
        TextField addUsernameField = new TextField();
        addUsernameField.setPromptText("Username");
        PasswordField addPasswordField = new PasswordField();
        addPasswordField.setPromptText("Password");
        Button addButton = new Button("Add Customer");
        addButton.setOnAction(e -> {
            String username = addUsernameField.getText();
            String password = addPasswordField.getText();
            File customerFile = new File(username + ".txt");
            
            if (customerFile.exists()) {
                showAlert("User Exists", "A customer with this username already exists.");
            } else {
                manager.addCustomer(username, password);
                showAlert("Add Customer", "Customer " + username + " has been added.");
            }
            addUsernameField.clear();
            addPasswordField.clear();
        });

        // delete user
        TextField deleteUsernameField = new TextField();
        deleteUsernameField.setPromptText("Username to Delete");
        Button deleteButton = new Button("Delete Customer");
        deleteButton.setOnAction(e -> {
            String username = deleteUsernameField.getText();
            File customerFile = new File(username + ".txt");
            if (!customerFile.exists()) {
                showAlert("Delete Customer", "No customer with this username.");
            } else {
                boolean deleted = manager.deleteCustomer(username);
                if (deleted) {
                    showAlert("Delete Customer", "Customer " + username + " was successfully deleted.");
                } else {
                    showAlert("Delete Customer", "Failed to delete customer " + username + ".");
                }
            }
            deleteUsernameField.clear();
        });

        // logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> {
            start(primaryStage);
        });

        layout.getChildren().addAll(new Label("Add New Customer:"), addUsernameField, addPasswordField, addButton, new Label("Delete Customer:"), deleteUsernameField, deleteButton, logoutButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void showCustomerScreen(Stage primaryStage, Customer customer, String username) {
        primaryStage.setTitle("Customer Dashboard - " + username);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        // display
        Label balanceLabel = new Label("Balance: $" + customer.getBalance());
        layout.getChildren().add(balanceLabel);
        Label levelLabel = new Label("Level: " + customer.level.getClass().getSimpleName());
        layout.getChildren().add(levelLabel);

        // deposit
        TextField depositAmountField = new TextField();
        depositAmountField.setPromptText("Deposit Amount");
        Button depositButton = new Button("Deposit");
        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(depositAmountField.getText());
                if (amount > 0) {
                    customer.deposit(amount);
                    balanceLabel.setText("Balance: $" + String.format("%.2f", customer.getBalance())); 
                    levelLabel.setText("Level: " + customer.level.getClass().getSimpleName());                    
                    updateCustomerFile(customer, username); // Assuming this method updates the file
                } else {
                    showAlert("Invalid Amount", "Please enter a positive number.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number.");
            }
            depositAmountField.clear();
        });
        layout.getChildren().addAll(depositAmountField, depositButton);
        
        // withdraw
        TextField withdrawAmountField = new TextField();
        withdrawAmountField.setPromptText("Withdraw Amount");
        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(withdrawAmountField.getText());
                if (amount <= 0) {
                    showAlert("Invalid Amount", "Please enter a positive number for withdrawal.");
                    return;
                }
                if (customer.getBalance() >= amount) {
                    customer.withdraw(amount);
                    balanceLabel.setText("Balance: $" + customer.getBalance());
                    levelLabel.setText("Level: " + customer.level.getClass().getSimpleName());                    
                    updateCustomerFile(customer, username);
                } else {
                    showAlert("Insufficient Funds", "Your balance is too low for this withdrawal.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number.");
            } finally {
                withdrawAmountField.clear();
            }
        });
        layout.getChildren().addAll(withdrawAmountField, withdrawButton);
        
        // purchases
        TextField purchaseAmountField = new TextField();
        purchaseAmountField.setPromptText("Purchase Amount ($50 or more)");
        Button purchaseButton = new Button("Make Purchase");
        purchaseButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(purchaseAmountField.getText());
                double totalAmount = customer.level.purchase(amount);
                if (amount < 50) {
                    showAlert("Invalid Amount", "Purchase must be $50 or more.");
                } else if (customer.getBalance() >= totalAmount) {
                    customer.purchase(amount);
                    balanceLabel.setText("Balance: $" + customer.getBalance());
                    levelLabel.setText("Level: " + customer.level.getClass().getSimpleName());
                    updateCustomerFile(customer, username);
                } else {
                    showAlert("Insufficient Funds", "Your balance is too low for this purchase.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number.");
            }
            purchaseAmountField.clear();
        });
        layout.getChildren().addAll(purchaseAmountField, purchaseButton);
        layout.setAlignment(Pos.CENTER);

        // logout
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> {
            start(primaryStage);
        });
        layout.getChildren().add(logoutButton);
        
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void updateCustomerFile(Customer customer, String username) {
        Path path = Paths.get(username + ".txt");
        try {
            List<String> lines = Files.readAllLines(path);

            if (!lines.isEmpty()) {
                lines.set(lines.size() - 1, String.valueOf(customer.getBalance()));
                Files.write(path, lines);
            }
        } catch (IOException e) {
            System.err.println("Failed to update the balance for " + username + ": " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
