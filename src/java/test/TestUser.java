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
public class TestUser {

    public static void main(String[] args) {
        UserHelper helper = new UserHelper();
        System.out.println("daftar email ");
        List<User> list = helper.userList();
        for (User temp : list) {
            System.out.println(temp.getEmail() + "," + temp.getName());
        }

        boolean test = helper.isUserExist("danang@usd.ac.id");
        System.out.println("test = " + test);

    }
}
