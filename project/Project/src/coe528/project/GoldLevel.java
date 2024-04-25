/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class GoldLevel extends Level {

    public GoldLevel(Customer customer) {
        super(customer);
    }

    @Override
    public void changeLevel() {
        double balance = customer.getBalance();
        
        if (balance >= 20000) {
            customer.setLevel(new PlatinumLevel(customer));
        } else if (balance < 10000) {
            customer.setLevel(new SilverLevel(customer));
        }
    }
    
    @Override
    public double purchase(double amount) {
        return amount + 10;
    }

}
