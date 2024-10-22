package org.example.eticaretapp.utility.data;

import org.example.eticaretapp.entity.Auth;
import org.example.eticaretapp.entity.Product;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.entity.enums.Category;
import org.example.eticaretapp.entity.enums.Role;
import org.example.eticaretapp.entity.enums.Status;
import org.example.eticaretapp.repository.AuthRepository;
import org.example.eticaretapp.repository.ProductRepository;
import org.example.eticaretapp.repository.UserRepository;
import org.example.eticaretapp.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GenerateData implements ApplicationRunner {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //createUser();
        createProduct();
    }

    private void createUser() {
        Auth auth = Auth.builder()
                .username("jsmith")
                .password(encryptionService.encryptPassword("j12345"))
                .role(Role.USER)
                .build();

        Auth auth2 = Auth.builder()
                .username("acolak")
                .password(encryptionService.encryptPassword("a12345"))
                .role(Role.SELLER)
                .build();

        authRepository.saveAll(List.of(auth,auth2));


        User user = User.builder()
                .name("John")
                .surname("Smith")
                .email("john.smith@gmail.com")
                .phone("05559876541")
                .address("Amerika")
                .birthdate(LocalDate.of(2000, 7, 25))
                .authId(auth.getId())
                .build();

        User user2 = User.builder()
                .name("Ayşe")
                .surname("Çolak")
                .email("ayse.colak@gmail.com")
                .phone("05559876541")
                .address("Elazığ")
                .birthdate(LocalDate.of(2005, 7, 25))
                .authId(auth2.getId())
                .build();
        userRepository.saveAll(List.of(user, user2));


    }

    public void createProduct(){

        Product product = Product.builder()
                .category(Category.COMPUTER)
                .brand("Casper")
                .name("Casper Excalibur G870.1270-DFB0X-B")
                .description("Performanslı bilgisayar")
                .price(38_999D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product2 = Product.builder()
                .category(Category.COMPUTER)
                .brand("MSI")
                .name("MSI CYBORG 15 A13VF-892XTR Intel Core i7 13620H ")
                .description("Hızlı bilgisayar")
                .price(44_999D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product3 = Product.builder()
                .category(Category.COMPUTER)
                .brand("Apple")
                .name("MacBook Air M1 Çip 16GB 256GB SSD macOS 13\" Taşınabilir Bilgisayar Uzay Grisi Z1240009K")
                .price(34_999D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product4 = Product.builder()
                .category(Category.COMPUTER)
                .brand("Apple")
                .name("MacBook Air M3 Çip 8GB 256GB SSD macOS 13 Taşınabilir Bilgisayar Gece Yarısı MRXV3TU/A")
                .price(45_999D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product5 = Product.builder()
                .category(Category.GIRLS_FASHION)
                .brand("Rayban")
                .name("Rayban RB4187 622/8G Erkek Güneş Gözlüğü")
                .description("Erkek Güneş Gözlüğü")
                .price(4_550D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product6 = Product.builder()
                .category(Category.COMPUTER)
                .brand("Gucci")
                .name("Gg 0340S 006 Güneş Gözlüğü")
                .description("Kadın Güneş Gözlüğü")
                .price(12_798D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product7 = Product.builder()
                .category(Category.BOOKS)
                .brand("İş Bankası")
                .name(" Altıncı Koğuş - Anton Çehov")
                .price(34_04D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product8 = Product.builder()
                .category(Category.BOOKS)
                .brand("İş Bankası")
                .name("Otomatik Portakal - Anthony Burgess")
                .price(54_0D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product9 = Product.builder()
                .category(Category.BOYS_FASHION)
                .brand("Casio")
                .name("MTP-1374D-1AVDF Standart Erkek Kol Saati")
                .price(2_600D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        Product product10 = Product.builder()
                .category(Category.BOYS_FASHION)
                .brand("Ferruci")
                .name("Gümüş Çelik Erkek Kol Saati 3 Atm Su Geçirmez Paslanmaz")
                .price(3_331D)
                .stockQuantity(100)
                .sellerId(2L)
                .status(Status.ACTIVE)
                .build();

        productRepository.saveAll(List.of(product,product2,product3,product4,product5,product6,product7,product8,product9,product10));
    }

}
