/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import helper.LogHelper;

/**
 *
 * @author user only
 */
public class TestLog1 {
    public static void main(String[] args) {
        LogHelper helperLog = new LogHelper();
        helperLog.saveLog("test");
    }
}
