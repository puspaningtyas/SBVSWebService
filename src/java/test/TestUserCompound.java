/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import helper.UserCompoundHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.UserCompound;

/**
 *
 * @author user only
 */
public class TestUserCompound {
    public static void main(String[] args) {
        
            UserCompoundHelper helper = new UserCompoundHelper();
            UserCompound result = helper.isCompoundActive("ccccci");
//            System.out.println(result.getResult());
            System.out.println(result);
        
    }
    
}
