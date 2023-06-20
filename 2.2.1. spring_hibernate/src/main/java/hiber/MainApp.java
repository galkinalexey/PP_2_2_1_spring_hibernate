package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Ferrari", 1963)));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Ford Mustang", 1967)));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Chevrolet Impala", 1974)));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("McLaren", 1983)));

      List<User> users = userService.listUsers();
      printUsersList(users);

      System.out.println("----------------------------------------------------------");

      List<User> usersListByCar = userService.getUserByModelAndSeries("Ford Mustang", 1967);

      printUsersList(usersListByCar);

      context.close();
   }

   public static void printUsersList (List<User> list) {
      for (User user : list) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
   }
}
