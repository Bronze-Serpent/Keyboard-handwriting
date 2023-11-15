package com.ivancha;

import com.ivancha.entity.Password;
import com.ivancha.entity.User;
import com.ivancha.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Map;


public class Main {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            var dima = User.builder()
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
            session.persist(password);

            session.getTransaction().commit();
        }
    }
}