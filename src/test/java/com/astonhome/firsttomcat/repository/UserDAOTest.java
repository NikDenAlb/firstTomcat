package com.astonhome.firsttomcat.repository;

import com.astonhome.firsttomcat.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.astonhome.firsttomcat.CONST.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOTest {
    private UserDAO userDAO;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(USERNAME1, USER1),
                Arguments.of(USERNAME2, USER2),
                Arguments.of(USERNAME3, USER3)
        );
    }

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    @Order(5)
    void getUser() {
        assertNull(userDAO.getUser(2));
        Long id = userDAO.addUser(USER1).getId();
        assertEquals(USERNAME1, userDAO.getUser(id).getName());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    @Order(1)
    void addUser(String name, User user) {
        user = userDAO.addUser(user);

        assertNotNull(user.getId());
        assertEquals(name, user.getName());
    }

    @Test
    @Order(2)
    void getAllUsers() {
        List<User> users = userDAO.getAllUsers();

        List<String> names = users.stream().map(User::getName).toList();

        assertThat(names).contains(USERNAME1, USERNAME2, USERNAME3);
    }

    @Test
    @Order(3)
    void updateUser() {
        User user = userDAO.addUser(USER1);
        User user2 = userDAO.addUser(USER1);
        userDAO.updateUser(user.getId(), USER2);
        assertEquals(USERNAME2, userDAO.getUser(user.getId()).getName());

        userDAO.updateUser(user2.getId(), USER3);
        assertEquals(USERNAME3, userDAO.getUser(user2.getId()).getName());
    }

    @Test
    @Order(4)
    void deleteUser() {
        userDAO.deleteUser(2);
        assertNull(userDAO.getUser(2));
    }

    @Test
    void getAllUserByCoachId() {
    }
}