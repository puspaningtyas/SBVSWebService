/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import helper.UserHelper;
import java.util.List;
import pojos.User;

/**
 *
 * @author user only
 */
public class TestAddUser {
    public static void main(String[] args) {
        UserHelper helper = new UserHelper();
        System.out.println("daftar email ");
        helper.saveDataUser("test@usd.ac.id", "testing", "USD", "Yogyakarta", "08987654321");
    }
 
}
