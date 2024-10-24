package org.example.eticaretapp.utility.data;

import jakarta.annotation.PostConstruct;
import org.example.eticaretapp.entity.Auth;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.entity.UserRole;
import org.example.eticaretapp.entity.enums.Role;
import org.example.eticaretapp.repository.AuthRepository;
import org.example.eticaretapp.repository.ProductRepository;
import org.example.eticaretapp.repository.UserRepository;
import org.example.eticaretapp.repository.UserRoleRepository;
import org.example.eticaretapp.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GenerateData {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private UserRoleRepository userRoleRepository;

    //@PostConstruct
   public void createData() {

        if(productRepository.findAll().isEmpty()){
            createUser();
            createProduct();
            createUserRole();

        }

    }
    private void createUserRole(){
        UserRole userRole1 = UserRole.builder()
                .userId(1L)
                .role("USER")
                .build();

        UserRole userRole2 = UserRole.builder()
                .userId(2L).role("SELLER").build();
        userRoleRepository.saveAll(List.of(userRole1, userRole2));
    }

    private void createUser() {
        Auth auth = Auth.builder()
                .username("jsmith")
                .password(encryptionService.encryptPassword("Sifre123"))
                .role(Role.USER)
                .build();

        Auth auth2 = Auth.builder()
                .username("acolak")
                .password(encryptionService.encryptPassword("Sifre123"))
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

    
    }

}