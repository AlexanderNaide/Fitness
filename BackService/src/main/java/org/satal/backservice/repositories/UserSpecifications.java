package org.satal.backservice.repositories;

import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {


    public static Specification<User> equalRole(Role role){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("role"), role);
    }

    public static Specification<User> equalRoleById(Long role){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("role").get("id"), role);
    }

    public static Specification<User> equalSpecialization(Specialization specialization){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("specialization"), specialization);
    }

    public static Specification<User> equalSpecializationById(Long specialization){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("specialization").get("id"), specialization);
    }

    public static Specification<User> nameLike(String value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", value));
    }

    public static Specification<User> surnameLike(String value){
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("article").as(String.class), String.format("%%%s%%", title));
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), String.format("%%%s%%", value));
    }

}
