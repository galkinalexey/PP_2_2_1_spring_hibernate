package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List<User> getUserByModelAndSeries(String model, int series) {

      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car car where car.model = :model and car.series = :series")
              .setParameter("model", model)
              .setParameter("series", series);

      List<User> listUsers = new ArrayList<>();

      for (Car car : query.getResultList()) {
         listUsers.add(car.getUser());
      }

      return listUsers;
   }

}
