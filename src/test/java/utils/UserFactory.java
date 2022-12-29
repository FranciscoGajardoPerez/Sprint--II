package utils;

import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.roles.Roles;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {
    public static User createUserWithRoleUserFran (){
        User Cele = createUserWithRoleSellerCele();

        User user = User.builder()
                .userId(1)
                .userName("Fran")
                .userRol(Roles.USER)
                .followed(new ArrayList<>())
                .followers(List.of(Cele))
                .build();
        return user;
    }

    public static User createUserWithRoleUserBrahi (){
        User user = User.builder()
                .userId(2)
                .userName("Brahi")
                .userRol(Roles.USER)
                .build();
        return user;
    }

    public static User createUserWithRoleSellerMati (){
        User user = User.builder()
                .userId(6)
                .userName("Mati")
                .followed(List.of(
                        createUserWithRoleUserBrahi(),
                        createUserWithRoleUserFran()
                ))
                .followers(List.of(
                        createUserWithRoleUserBrahi(),
                        createUserWithRoleUserFran()
                ))
                .userRol(Roles.SELLER)
                .posts(new ArrayList<>())
                .build();
        return user;
    }

    public static User createUserWithRoleSellerCele (){
        User user = User.builder()
                .userId(8)
                .userName("Cele")
                .followed(new ArrayList<>())
                .followers(new ArrayList<>())
                .userRol(Roles.SELLER)
                .build();
        return user;
    }

    public static User createUserWithFollowedWithPosts(Integer userId){
        List<User> followed = new ArrayList<>();

        followed.add(createUserWithPost(8));

        return User.builder()
                .userId(userId)
                .followed(followed)
                .userRol(Roles.USER)
                .userName("Jean")
                .build();
    }

    public static User createUserWithPost(Integer userId){
       return User.builder().userId(userId).posts(PostFactory.createsListPost()).build();
    }
}
