package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.Maintenance;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.repositories.RoleRepository;
import org.satal.backservice.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> getUser(AuthRequest authRequest){
        return userRepository.findUserByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
    }
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public Page<User> findAll(Role role, Specialization specialization, String value, Integer page){ // вот тут попробовать переделать на LOnd Id, по каждой сущности

        Specification<User> spec = Specification.where(null);

        if(minPrice != null){
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if(maxPrice != null){
            spec = spec.and(ProductSpecifications.priceLessenOrEqualsThan(maxPrice));
        }
        if(title != null){
            spec = spec.and(ProductSpecifications.titleLike(title)).or(ProductSpecifications.artLike(title));
        }

        if(subCategories != null){
            Optional<Category> categoryOptional = categoriesService.findById(subCategories);
            if(categoryOptional.isPresent()){
                spec = spec.and(ProductSpecifications.category(categoryOptional.get()));
            }
        } else if (categories != null) {
            Optional<Category> categoryOptional = categoriesService.findById(categories);
            if(categoryOptional.isPresent()){
                spec = spec.and(ProductSpecifications.subCategory(categoryOptional.get()));
            }
        }

        if(man != null){
            long manId = Long.parseLong(Objects.requireNonNull(Arrays.stream(man.split(",")).findFirst().orElse(null)));
            Optional<Manufacturer> manufacturerOptional = manufacturerService.findById(manId);
            if(manufacturerOptional.isPresent()){
                spec = spec.and(ProductSpecifications.manufacturer(manufacturerOptional.get()));
            }
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }
}
