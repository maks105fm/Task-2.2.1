package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carrService = context.getBean(CarService.class);

      carrService.add(new Car("BMW1", 1));
      carrService.add(new Car("BMW2", 2));
      carrService.add(new Car("BMW3", 3));
      carrService.add(new Car("BMW4", 4));
      List<Car> carList = carrService.listCars();
      for (Car elem:carList){
         System.out.println(elem);
      }

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", carList.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", carList.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", carList.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", carList.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.getUsersByModelAndSeries("BMW2",2);

      context.close();
   }
}
