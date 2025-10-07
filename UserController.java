import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.hibernate.model.Book;
import com.journaldev.hibernate.model.BookSIZE;
import com.journaldev.hibernate.model.Lesson;
import com.journaldev.hibernate.model.Storage;

import spring_boot_data.BookDATA;

@RestController
@RequestMapping("/похххх, потом сделаю как надо, главное не проебать сделать")
public class UserController {

    /**
     * Получение списка всех пользователей.
     * Возвращает пустой список, если пользователей нет.
     */
    @GetMapping("/")
    public List<UserDATA> getAllUsers() {
        // Создаем пустой список для возврата данных о пользователях
        List<UserDATA> allUsers = new ArrayList<>();
        return allUsers;
    }

   
     ///Получение пользователя по его ID.
   
    @GetMapping("/{id}")
    public UserDATA getUserById(@PathVariable Integer id) {
        // Создаем новый объект UserDATA и устанавливаем переданный ID
        UserDATA newUserData = new UserDATA();
        newUserData.setId(id);
        return newUserData;
    }

    /**
     * Создание нового пользователя.
     * Проверяет, что входные данные не null и ID не задан (новый пользователь).
     */
    @PostMapping("/")
    public UserDATA createUser(@RequestBody UserDATA data) {
        // Проверка входных данных
        if (data == null || data.getId() != null) {
            throw new IllegalArgumentException("Data cannot be null or contain an ID.");
        }
        Repository<User> userRepository = new Repository<>();
        // Сохранение пользователя в базе и возврат DTO
        return toUserDATA(userRepository.save(toUser(data)));
    }

    ///Обновление данных пользователя.
     
    @PutMapping("/")
    public String updateUser() {
        return "Update User";
    }

    ///Удаление нахуй пользователя.
     
    @DeleteMapping("/")
    public String deleteUser() {
        return "Delete User";
    }

    private User toUser(UserDATA data) {
        User user = new User();
        user.setId(data.getId());
        user.setSurnameString(data.getSurnameString());
        user.setUsername(data.getUsername());
        user.setUserEmail(data.getUserEmail());
        user.setPassword(data.getPassword());
        return user;
    }

  
     ///Преобразование сущности User в DTO UserDATA для возврата клиенту.
     
    private UserDATA toUserDATA(User user) {
        UserDATA data = new UserDATA();
        data.setId(user.getId());
        data.setSurnameString(user.getSurnameString());
        data.setUsername(user.getUsername());
        data.setUserEmail(user.getUserEmail());
        data.setPassword(user.getPassword());
        return data;
    }

    ///бляяяяя, ещё таблицу в майескюель создать и подклбчить её к проекту, ну а фронт на тебя оставляю, я не ебу как там чо делать