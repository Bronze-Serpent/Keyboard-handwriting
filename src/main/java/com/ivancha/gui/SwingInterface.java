package com.ivancha.gui;

import com.ivancha.gui.panel.RegistrationPanel;
import com.ivancha.mapper.PasswordCreateMapper;
import com.ivancha.mapper.PasswordReadMapper;
import com.ivancha.mapper.UserCreateMapper;
import com.ivancha.mapper.UserReadMapper;
import com.ivancha.repository.PasswordRepository;
import com.ivancha.repository.UserRepository;
import com.ivancha.service.PasswordService;
import com.ivancha.service.UserService;
import com.ivancha.util.HibernateUtil;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;


@RequiredArgsConstructor
public class SwingInterface {


    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            var validatorFactory = Validation.buildDefaultValidatorFactory();
            var validator = validatorFactory.getValidator();

            var passwordReadMapper = new PasswordReadMapper();
            var userReadMapper = new UserReadMapper(passwordReadMapper);
            var userCreateMapper = new UserCreateMapper();
            var userRepository = new UserRepository(session);
            var userService = new UserService(userRepository, userCreateMapper, userReadMapper, validator);

            var passwordRepository = new PasswordRepository(session);
            var passwordCreateMapper = new PasswordCreateMapper(userRepository);
            var passwordService = new PasswordService(passwordRepository, passwordCreateMapper, validator);

            showFrame(userService, passwordService);

            session.getTransaction().commit();
        }
    }


    private static void showFrame(UserService userService, PasswordService passwordService) {

        JFrame frame = new JFrame("Пароли, парольчики, паролища");

        JPanel regPanel = new RegistrationPanel(userService, passwordService);

        frame.getContentPane().add(regPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

