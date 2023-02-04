package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

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
   public User getUserByModelAndSeries(String model, int series){
      TypedQuery<Car> car = sessionFactory.getCurrentSession()
              .createQuery("from Car where model =: modelCar and series =: seriesCar");
      car.setParameter("modelCar", model);
      car.setParameter("seriesCar", series);
      List<Car> carList = car.getResultList();
      List<User> userList = listUsers().stream().filter(user -> user.getUserCar() == carList.get(0)).collect(Collectors.toList());
      System.out.println(userList.get(0).getFirstName() + " Владелец");
   return userList.get(0);
   }

}
