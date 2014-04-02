/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.oktyabr;

import com.barsoft.oktyabr.entities.SeedTypes;
import java.io.File;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author maks
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            AnnotationConfiguration ac = new AnnotationConfiguration();
            //Это нам нужно для того, чтобы мы добавили все наши классы сущности. 
            //каждый ваш Entity здесь нужно прописать, не пропишете - не будет работать. 
            ac.addAnnotatedClass(SeedTypes.class);
            //Вот мы собственно и создали нашу Фабрику сессий. 
            //Она нужна т.к с БД мы работаем через сессии
            //Подробности будут чуть позже, пока знайте, как ее сделать и как с ней работать. 
            
            sessionFactory = ac.configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
