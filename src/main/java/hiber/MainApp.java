package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car user1Car = new Car("BMW 525i", 8031990);
      Car user2Car = new Car("Gaz 2110", 4563352);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", user1Car));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", user2Car));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }

      User user1 = userService.getUserWithCar(user1Car);
      StringBuilder result = new StringBuilder();
      String out = result.append(user1.getFirstName()).append(" ").append(user1.getLastName()).append(" владеет автомобилем ").append(user1.getCar().getModel()).toString();
      System.out.println(out);

      User user2 = userService.getUserWithCar(user2Car);
      StringBuilder result2 = new StringBuilder();
      String out2 = result2.append(user2.getFirstName()).append(" ").append(user2.getLastName()).append(" владеет автомобилем ").append(user2.getCar().getModel()).toString();
      System.out.println(out2);
      context.close();
   }
}
