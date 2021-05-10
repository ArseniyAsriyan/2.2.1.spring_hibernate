package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserWithCar(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User where car.model = :firstParam and car.series = :secondParam");
      query.setParameter("firstParam", car.getModel());
      query.setParameter("secondParam", car.getSeries());
      User user = query.getResultList().get(0);
      return user;
   }

}
