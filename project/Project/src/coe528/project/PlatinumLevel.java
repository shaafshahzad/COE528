/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author Shaaf Shahzad, #50117227
 */
public class PlatinumLevel extends Level {

    public PlatinumLevel(Customer customer) {
        super(customer);
    }

    @Override
    public void changeLevel() {
        double balance = customer.getBalance();
        
        if (balance < 20000) {
            customer.setLevel(new GoldLevel(customer));
        }
    }
    
    @Override
    public double purchase(double amount) {
        return amount;
    }

}
