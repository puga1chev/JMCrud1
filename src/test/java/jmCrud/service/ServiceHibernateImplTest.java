package jmCrud.service;

import jmCrud.model.*;
import org.junit.jupiter.api.*;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class ServiceHibernateImplTest {

    ObjectService<User> userService = null;
    ObjectService<Role> roleService = null;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        roleService = new RoleServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    //TODO многопоточные тесты?
    @Test
    void someWorkWithDB() {
        // prepare
        // roles

        Role roleXom = roleService.getByField("id", "1");
        roleXom.setRolename("Хомяк " + new Random().nextInt());
        roleService.update(roleXom);

        //users
        int usersCount = userService.getAll(null).size();
        // add user
        User newUser = new User(
                0L,
                "Test User",
                "Test Login " + new Random().nextInt(),
                "test pass",
                roleXom
        );
        userService.insert(newUser);
        assertTrue(newUser.getId() != 0L, "Пользователь не был добавлен");
        assertTrue(userService.getAll(null).size() != usersCount + 1, "Не удалось добавить запись в БД");
        if (newUser.getId() == 0L) {
            return;
        }
        // get user from db
        assertEquals(userService.getById(newUser.getId()).getLogin(), newUser.getLogin(), "Не удалось получить из базы пользователя с ИД " + newUser.getId());
        // change user
        newUser.setUsername("Гоголь Николай Васильевич");
        userService.update(newUser);
        assertTrue(userService.getById(newUser.getId()).getUsername().equals("Гоголь Николай Васильевич"), "Не удалось получить из базы пользователя с ИД " + newUser.getId());
        // delete user
        userService.delete(newUser.getId());
        assertEquals(userService.getAll(null).size(), usersCount, "Не удалось удалить добавленного пользователя из БД");
    }
}