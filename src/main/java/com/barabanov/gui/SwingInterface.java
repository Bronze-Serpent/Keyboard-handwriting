package com.barabanov.gui;

import com.barabanov.gui.window.PasswordFrame;

import static com.barabanov.service.ServiceUtil.startServices;


public class SwingInterface {


    public static void main(String[] args) {

        var serviceResources = startServices();

        var frame = new PasswordFrame(serviceResources);
        frame.setVisible(true);
    }

}

