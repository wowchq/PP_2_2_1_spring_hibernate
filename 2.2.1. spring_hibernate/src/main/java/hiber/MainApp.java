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


      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


      //Добавление пользователей с машинами в БД
      User userWithCar1 = new User("User5", "Lastname5", "user5@mail.ru");
      userWithCar1.setUserCar(new Car("BMW", 111));
      userService.add(userWithCar1);
      User userWithCar2 = new User("User6", "Lastname6", "user6@mail.ru");
      userWithCar2.setUserCar(new Car("Mercedes", 222));
      userService.add(userWithCar2);
      User userWithCar3 = new User("User7", "Lastname7", "user7@mail.ru");
      userWithCar3.setUserCar(new Car("BMW", 333));
      userService.add(userWithCar3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCarModelAndSeries("BMW", 111));

      context.close();
   }
}
