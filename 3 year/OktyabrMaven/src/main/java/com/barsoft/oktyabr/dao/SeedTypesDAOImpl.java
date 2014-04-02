/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.oktyabr.dao;

import com.barsoft.oktyabr.HibernateUtil;
import com.barsoft.oktyabr.entities.SeedTypes;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author maks
 */
class SeedTypesDAOImpl implements SeedTypesDAO {

    public SeedTypesDAOImpl() {
    }

    @Override
    public List<SeedTypes> getSeedTypes() {
        List<SeedTypes> result = null;
//Создаем сессию, она нужна для использования транзакций
//Грубо говоря, транзакция - это как точка восстановления, если не прошла до конца, то все изменения откатываются.
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
//Criteria используется  для запроса с целью получения данных из БД            
//Такой формулировки, думаю, Вам пока хватит
//Параметром мы передаем тот класс-сущность, который используем. Если бы данные получали из таблицы Cart то передавать
//надо было бы Cart.class
            Criteria criteria = session.createCriteria(SeedTypes.class);

            result = (List<SeedTypes>) criteria.list();

            session.getTransaction().commit();

        } catch (Exception e) {
//Обработку исключений обязательно пишите. Но это я оставлю Вам на самостоятельную работу.           
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public void addSeedType(SeedTypes seedType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(seedType);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
