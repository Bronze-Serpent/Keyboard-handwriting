package com.ivancha;

import com.ivancha.dto.UserReadDto;
import com.ivancha.entity.Password;
import com.ivancha.entity.User;
import com.ivancha.mapper.PasswordReadMapper;
import com.ivancha.mapper.UserCreateMapper;
import com.ivancha.mapper.UserReadMapper;
import com.ivancha.repository.UserRepository;
import com.ivancha.service.UserService;
import com.ivancha.util.HibernateUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

/*
            var validatorFactory = Validation.buildDefaultValidatorFactory();
            var validator = validatorFactory.getValidator();

            var userService = new UserService(new UserRepository(session), new UserCreateMapper(), new UserReadMapper(new PasswordReadMapper()), validator);
            var users = userService.findAll();
            users.forEach(user -> System.out.println(user.id() + " " + user.name() + user.passwordReadDto()));*/


            /*var dima = User.builder()
                    .nickname("Dima")
                    .build();

            Password password = new Password(
                    null,
                    "qwerty",
                    dima,
                    Map.of(1, 15, 2, 16, 3, 10),
                    Map.of(1, 23, 2, 23, 3, 12)
            );

            session.persist(dima);
            session.persist(password);*/

            session.getTransaction().commit();
        }
    }


    public static void test() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            // TODO: 16.11.2023 почему тут в конце запрос в password
            var password = session.get(Password.class, 1);
            System.out.println(password.getId());
            System.out.println(password.getValue());
            System.out.println(password.getKeyPressTime());
            System.out.println(password.getTimeBetweenPresses());
            System.out.println(password.getUser());

            session.getTransaction().commit();
        }
    }
}