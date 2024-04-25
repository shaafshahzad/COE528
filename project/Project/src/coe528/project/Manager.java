/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class Manager {
    private final String username = "admin";
    private final String password = "admin";

    public Manager() {
        createFile(username, password);
    }
        
    public void addCustomer(String username, String password) {
        createFile(username, password);
    }

    public boolean deleteCustomer(String username) {
        File file = new File(username + ".txt");
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }
    
    private void createFile(String username, String password) {
        try {
            File login = new File(username + ".txt");
            if (login.createNewFile()) {
                try (FileWriter writer = new FileWriter(login)) {
                    writer.write(username + "\n" + password);
                    if (username != "admin") {
                        writer.write("\n100");
                    }
                }
            } else {
                System.out.println("file already exists");
            }
        } catch (IOException error) {
            System.out.println("Error: " + error);
        }
    }
}
