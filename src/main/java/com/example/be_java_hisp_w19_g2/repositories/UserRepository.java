package com.example.be_java_hisp_w19_g2.repositories;

import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.entities.Product;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.handlers.UserNotFoundException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotSeller;
import com.example.be_java_hisp_w19_g2.roles.Roles;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository {
    public List<User> users = new ArrayList<>();

    public UserRepository() {
        this.users = userList();
    }

    public List<User> userList() {
        //Lista auxiliar de foloweds para el usuario 11 (test 007)
        List<User> followed = new ArrayList<>();

        followed.add(new User(8, "Cele", Roles.SELLER));
        followed.add(new User(14, "Juancito", Roles.SELLER));

        //Lista auxiliar de usuario con posts (test 006)
        Product product1 = new Product("Longboard", "Sport",    "Adidas", "Black",     "N/A");
        Product product2 = new Product("Skate", "Sport",    "Nike", "Grey",     "N/A");
        Product product3 = new Product("Bike", "Sport",    "Electro", "Red",     "N/A");

        List<Post> posts = new ArrayList<>();

        posts.add(new Post(1, 8, LocalDate.now(), product1, 20, 1243.2));
        posts.add(new Post(2, 8, LocalDate.now().minusDays(1), product2, 20, 1243.2));
        posts.add(new Post(3, 8, LocalDate.now().minusDays(2), product3, 20, 1243.2));
        //Usuarios con post para el test 007

        users.add(new User(8, posts));

        users.add(new User(1, "Fran", Roles.USER));
        users.add(new User(2, "Brahi", Roles.USER));
        users.add(new User(3, "Erick", Roles.USER));
        users.add(new User(4, "Juli", Roles.USER));
        users.add(new User(5, "Martin", Roles.USER));
        users.add(new User(6, "Mati", Roles.SELLER, followed));
        users.add(new User(7, "Agus", Roles.SELLER));
        users.add(new User(8, "Cele", Roles.SELLER));
        users.add(new User(9, "Lu", Roles.SELLER));
        users.add(new User(10, "Nico", Roles.SELLER));
        users.add(new User(11, "Jean", Roles.USER, followed));
        users.add(new User(13, "Pepito", Roles.SELLER, followed));

        //usuario 12 para test 006
        users.add(new User(12, posts));


        return users;
    }

    public User getUserById(Integer userId) {
        return this.users.stream()
            .filter(u -> u.getUserId().equals(userId))
            .findFirst()
            .orElseThrow(() -> {
                throw new UserNotFoundException("User not found: " + userId);
            });
    }
}
