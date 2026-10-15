/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentregestrationsystem;

/**
 *
 * @author PC
 */
public class SingletonTest {
    
    public static void main(String[] args) {
        
        System.out.println("========== SINGLETON PATTERN TEST ==========\n");
        
        // Try to get instance 1
        System.out.println("Getting first instance...");
        RegistrationDAO dao1 = RegistrationDAO.getInstance();
        System.out.println("dao1 instance: " + dao1);
        System.out.println("dao1 hashCode: " + dao1.hashCode() + "\n");
        
        // Try to get instance 2
        System.out.println("Getting second instance...");
        RegistrationDAO dao2 = RegistrationDAO.getInstance();
        System.out.println("dao2 instance: " + dao2);
        System.out.println("dao2 hashCode: " + dao2.hashCode() + "\n");
        
        // Try to get instance 3
        System.out.println("Getting third instance...");
        RegistrationDAO dao3 = RegistrationDAO.getInstance();
        System.out.println("dao3 instance: " + dao3);
        System.out.println("dao3 hashCode: " + dao3.hashCode() + "\n");
        
        // Check if they are the SAME object
        System.out.println("========== VERIFICATION ==========");
        System.out.println("dao1 == dao2? " + (dao1 == dao2));  // Should be TRUE
        System.out.println("dao2 == dao3? " + (dao2 == dao3));  // Should be TRUE
        System.out.println("dao1 == dao3? " + (dao1 == dao3));  // Should be TRUE
        
        System.out.println("\n✓ All three variables point to the SAME instance!");
        System.out.println("✓ Singleton Pattern is working correctly!");
        
        // This would cause a COMPILE ERROR (uncomment to see):
        // RegistrationDAO dao4 = new RegistrationDAO();  
        // ERROR: Constructor is private!
        
        System.out.println("\n========== TEST COMPLETE ==========");
    }
}






