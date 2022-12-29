package utils;

import com.example.be_java_hisp_w19_g2.dtos.*;
import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PostFactory {

    static ModelMapper mapper = new ModelMapper();

    ProductFactory productFactory = new ProductFactory();

    public Post createAPost(Integer userId) {
        return Post.builder()
                .postId(1)
                .userId(userId)
                .category(123)
                .date(LocalDate.now())
                .price(1234.2)
                .product(productFactory.createProduct())
                .build();
    }


    public static List<Post> createsListPost() {
        Product product1 = new Product("Longboard", "Sport", "Adidas", "Black", "N/A");
        Product product2 = new Product("Skate", "Sport", "Nike", "Grey", "N/A");
        Product product3 = new Product("Bike", "Sport", "Electro", "Red", "N/A");

        List<Post> posts = new ArrayList<>();

        posts.add(new Post(1, 8, LocalDate.now(), product1, 20, 1243.2));
        posts.add(new Post(2, 8, LocalDate.now().minusDays(1), product2, 20, 1243.2));
        posts.add(new Post(3, 8, LocalDate.now().minusDays(2), product3, 20, 1243.2));

        return posts;
    }

    public static List<PostDTO> createsList(){
        ProductDTO product1 = new ProductDTO(null, "Longboard", "Sport", "Adidas", "Black", "N/A");
        ProductDTO product2 = new ProductDTO(null,"Skate", "Sport", "Nike", "Grey", "N/A");
        ProductDTO product3 = new ProductDTO(null,"Bike", "Sport", "Electro", "Red", "N/A");

        List<PostDTO> postsDTO = new ArrayList<>();

        postsDTO.add(new PostDTO( 8, LocalDate.now(), product1, 20, 1243.2));
        postsDTO.add(new PostDTO( 8, LocalDate.now().minusDays(1), product2, 20, 1243.2));
        postsDTO.add(new PostDTO( 8, LocalDate.now().minusDays(2), product3, 20, 1243.2));

        return postsDTO;
    }

    //crea un followedPostDTO ordenado ascendentemente
    public static FollowedPostsDTO createFollowedPostDTOAsc() {

        List<PostDTO> postsDTO =  createsList();

        Collections.sort(postsDTO, Comparator.comparing(PostDTO::getDate));

        return FollowedPostsDTO.builder()
                .posts(postsDTO)
                .userId(12)
                .build();
    }



    public static FollowedPostsDTO createFollowedPostDTODESC(Integer userId) {

        List<PostDTO> postsDTO = createsList();

        Collections.sort(postsDTO, (a, b) -> (b.getDate().compareTo(a.getDate())));

        return FollowedPostsDTO.builder()
                .posts(postsDTO)
                .userId(userId)
                .build();
    }
}
