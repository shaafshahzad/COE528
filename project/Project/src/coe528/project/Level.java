/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public abstract class Level {

    public Customer customer;

    public Level(Customer customer) {
        this.customer = customer;
    }
    
    public abstract void changeLevel();

    public abstract double purchase(double amount);

}
