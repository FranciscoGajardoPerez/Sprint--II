package utils;

import com.example.be_java_hisp_w19_g2.dtos.FollowerCountDTO;
import com.example.be_java_hisp_w19_g2.dtos.FollowersDTO;
import com.example.be_java_hisp_w19_g2.dtos.UserDTO;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.roles.Roles;

import java.util.ArrayList;
import java.util.List;

public class UserDTOFactory {
    //metodo creado para el test 007
    public static FollowerCountDTO createFollowersCountDTO(){
        return FollowerCountDTO.builder()
                .userId(6)
                .userName("Mati")
                .followersCount(2)
                .build();
    }

    public static FollowerCountDTO createFollowersCountWithDataDTO(){
        return FollowerCountDTO.builder()
                .userId(6)
                .userName("Mati")
                .followersCount(0)
                .build();
    }

    //método creado para el test 004
    public static FollowersDTO createFollowerDTO (){
        List <UserDTO> users = new ArrayList<>();
        users.add(createUserWithRoleUserDTOBrahi());
        users.add(createUserWithRoleUserDTOFran());
        FollowersDTO followersDTO = FollowersDTO
                .builder()
                .userId(6)
                .userName("Mati")
                .followed(users)
                .build();

        return followersDTO;
    }

    public static FollowersDTO createFollowerDTO2 (){
        List <UserDTO> users = new ArrayList<>();
        users.add(createUserWithRoleUserDTOBrahi());
        users.add(createUserWithRoleUserDTOFran());
        FollowersDTO followersDTO = FollowersDTO
                .builder()
                .userId(6)
                .userName("Mati")
                .followed(users)
                .build();

        return followersDTO;
    }

    public static FollowersDTO createFollowerDTO3 (){
        List <UserDTO> users = new ArrayList<>();
        users.add(UserDTO.builder()
                        .userId(8)
                        .userName("Cele").build());

        users.add(UserDTO.builder()
                        .userId(14)
                        .userName("Juancito")
                        .build());

        FollowersDTO followersDTO = FollowersDTO
                .builder()
                .userId(6)
                .userName("Mati")
                .followed(users)
                .build();

        return followersDTO;
    }

    public static UserDTO createUserWithRoleUserDTOFran (){
        UserDTO user = UserDTO
                .builder()
                .userId(1)
                .userName("Fran")
                .build();

        return user;
    }

    public static UserDTO createUserWithRoleUserDTOBrahi (){
        UserDTO user = UserDTO
                .builder()
                .userId(2)
                .userName("Brahi")
                .build();

        return user;
    }

    public static UserDTO createUserWithRoleSellerDTOCele (){
        UserDTO user = UserDTO
                .builder()
                .userId(8)
                .userName("Cele")
                .build();

        return user;
    }

    public static UserDTO createUserWithRoleSellerDTOAgus (){
        UserDTO user = UserDTO
                .builder()
                .userId(7)
                .userName("Agus")
                .build();

        return user;
    }

    public static UserDTO createUserResponseFollowsSeller(){
        return UserDTO.builder()
                .userId(10)
                .userName("Nico")
                .build();
    }
}
