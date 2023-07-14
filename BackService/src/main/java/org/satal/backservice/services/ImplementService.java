package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.gridClasses.Workout;
import org.satal.backservice.entities.gridClasses.ClubService;
import org.satal.backservice.entities.users.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImplementService {

    private final RoleService roleService;

    private final SpecializationService specializationService;

    private final UserService userService;

    private final SeasonTicketService seasonTicketService;

    private final MaintenanceService maintenanceService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final WorkoutService workoutService;

    public void init(){
        Role superUserRole = new Role();
        superUserRole.setTitleRole("super");
        roleService.save(superUserRole);

        Role adminRole = new Role();
        adminRole.setTitleRole("admin");
        roleService.save(adminRole);

        Role trainerRole = new Role();
        trainerRole.setTitleRole("trainer");
        trainerRole.setContainsSpecializations(true);
        roleService.save(trainerRole);

        Role userRole = new Role();
        userRole.setTitleRole("user");
        roleService.save(userRole);

        ClubService functionalMaintenance = new ClubService();
        functionalMaintenance.setTitle("ТРЕНАЖЕРНЫЙ ЗАЛ, КАРДИОТЕАТРЫ");
        functionalMaintenance.setIco("assets/images/services/gum.png");
        functionalMaintenance.setDescription("В тренажерных залах нашего клуба установлено профессиональное оборудование ведущих мировых производителей.");
        maintenanceService.save(functionalMaintenance);

        ClubService waterMaintenance = new ClubService();
        waterMaintenance.setTitle("ТРИ БАССЕЙНА");
        waterMaintenance.setIco("assets/images/services/pool.png");
        waterMaintenance.setDescription("Просторная аквазона нашего фитнес клуба включает три бассейна: тренировочный, детский, развлекательный бассейн с гидромассажными линиями и джакузи, а также банный комплекс, суши-бар, зоны отдыха и массажный кабинет.");
        maintenanceService.save(waterMaintenance);

        ClubService groupMaintenance = new ClubService();
        groupMaintenance.setTitle("ГРУППОВЫЕ ПРОГРАММЫ");
        groupMaintenance.setIco("assets/images/services/group.png");
        groupMaintenance.setDescription("Групповые программы в фитнес клубе «Три Океана» - это прекрасная физическая форма и отличная компания! Здесь никогда не бывает скучно!");
        maintenanceService.save(groupMaintenance);

        ClubService personalMaintenance = new ClubService();
        personalMaintenance.setTitle("ПЕРСОНАЛЬНЫЙ ТРЕНИНГ");
        personalMaintenance.setIco("assets/images/services/personal.png");
        personalMaintenance.setDescription("Персональный тренинг используется в любом направлении фитнеса: плавание и аквааэробика, тренажерный зал, аэробика, Mind Body, единоборства, танцевальные направления.");
        maintenanceService.save(personalMaintenance);

        Specialization functional = new Specialization();
        functional.setSpecializationTitle("functional");
        functional.setClubService(functionalMaintenance);
        specializationService.save(functional);

        Specialization water = new Specialization();
        water.setSpecializationTitle("water");
        water.setClubService(waterMaintenance);
        specializationService.save(water);

        Specialization group = new Specialization();
        group.setSpecializationTitle("group");
        group.setClubService(functionalMaintenance);
        specializationService.save(group);

        Specialization kids = new Specialization();
        kids.setSpecializationTitle("kids");
        specializationService.save(kids);

        Specialization massage = new Specialization();
        massage.setSpecializationTitle("massage");
        specializationService.save(massage);

        User superUser = new User();
        superUser.setLogin("super");
//        superUser.setPassword("123");
        superUser.setPassword(passwordEncoder.encode("123"));
        superUser.setRole(superUserRole);
        superUser.setName("Super");
        superUser.setSurname("Admin");
        superUser.setBackground("../images/contact.jpg");
        superUser.setAvatar("../images/gallery_5.jpg");
        userService.save(superUser);

        User trainer1 = new User();
        trainer1.setLogin("tr1");
//        trainer1.setPassword("123");
        trainer1.setPassword(passwordEncoder.encode("123"));
        trainer1.setRole(trainerRole);
        trainer1.setName("Trainer1");
        trainer1.setSurname("***");
        trainer1.setSpecialization(functional);
        userService.save(trainer1);

        User trainer2 = new User();
        trainer2.setLogin("tr2");
//        trainer2.setPassword("123");
        trainer2.setPassword(passwordEncoder.encode("123"));
        trainer2.setRole(trainerRole);
        trainer2.setName("Trainer2");
        trainer2.setSurname("***");
        trainer2.setSpecialization(water);
        userService.save(trainer2);

        User admin = new User();
        admin.setLogin("admin");
//        admin.setPassword("123");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRole(adminRole);
        admin.setName("Admin");
        admin.setSurname("***");
        userService.save(admin);

        User user0 = new User();
        user0.setLogin("user0");
//        user0.setPassword("123");
        user0.setPassword(passwordEncoder.encode("123"));
        user0.setRole(userRole);
        user0.setName("User0_Name");
        user0.setSurname("User0_Surname");
        userService.save(user0);

        for (int i = 1; i < 50; i++) {
            User user = new User();
            user.setLogin("user" + i);
//            user.setPassword("123");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRole(userRole);
            user.setName("User" + i + "_Name");
            user.setSurname("User" + i + "_Surname");
            userService.save(user);
        }

        SeasonTicket full = new SeasonTicket();
        full.setTitle("Полная");
        full.setPrice(BigDecimal.valueOf(20000.0));
        full.setDescription("Карта 18+. Посещение клуба не ограничено в часы работы клуба. Есть возможность заморозки на 60 дней.");
        seasonTicketService.save(full);

        SeasonTicket individual = new SeasonTicket();
        individual.setTitle("Индивидуальная");
        individual.setPrice(BigDecimal.valueOf(18000.0));
        individual.setDescription("Карта 18+. Посещение клуба не ограничено в часы работы клуба. Без возможности заморозки.");
        seasonTicketService.save(individual);

        SeasonTicket afternoon = new SeasonTicket();
        afternoon.setTitle("Дневная");
        afternoon.setPrice(BigDecimal.valueOf(16000.0));
        afternoon.setDescription("Карта 18+. Посещение клуба с 6:00 до 17:00. Есть возможность заморозки на 60 дней.");
        seasonTicketService.save(afternoon);

        SeasonTicket study = new SeasonTicket();
        study.setTitle("Студенческая");
        study.setPrice(BigDecimal.valueOf(16000.0));
        study.setDescription("Карта 18+. Посещение клуба не ограничено в часы работы клуба. Есть возможность заморозки на 60 дней. Оформляется при предъявлении студенческого билета.");
        seasonTicketService.save(study);

        SeasonTicket kidsTicket = new SeasonTicket();
        kidsTicket.setTitle("Детская");
        kidsTicket.setPrice(BigDecimal.valueOf(12000.0));
        kidsTicket.setDescription("Карта полного дня. Возраст от 3 до 13 лет. Посещение не ограничено в часы работы клуба. Возможность заморозки на 60 дней.");
        seasonTicketService.save(kidsTicket);

        Workout workout1 = new Workout();
        workout1.setGroup(true);
        workout1.setTime(LocalDateTime.of(2023, 7, 14, 16, 0));
        workout1.setTrainer(trainer1);
        workout1.setSpecialization(functional);
        workout1.setCustomers(List.of(user0));
        workoutService.save(workout1);

        Workout workout2 = new Workout();
        workout2.setGroup(true);
        workout2.setTime(LocalDateTime.of(2023, 7, 14, 20, 0));
        workout2.setTrainer(trainer1);
        workout2.setSpecialization(functional);
        workout2.setCustomers(List.of(user0));
        workoutService.save(workout2);

        Workout workout3 = new Workout();
        workout3.setGroup(true);
        workout3.setTime(LocalDateTime.of(2023, 7, 13, 18, 0));
        workout3.setTrainer(trainer1);
        workout3.setSpecialization(functional);
        workout3.setCustomers(List.of(user0));
        workoutService.save(workout3);

        Workout workout4 = new Workout();
        workout4.setGroup(true);
        workout4.setTime(LocalDateTime.of(2023, 7, 12, 16, 0));
        workout4.setTrainer(trainer1);
        workout4.setSpecialization(functional);
        workout4.setCustomers(List.of(user0));
        workoutService.save(workout4);

        Workout workout5 = new Workout();
        workout5.setGroup(true);
        workout5.setTime(LocalDateTime.of(2023, 7, 11, 16, 0));
        workout5.setTrainer(trainer1);
        workout5.setSpecialization(functional);
        workout5.setCustomers(List.of(user0));
        workoutService.save(workout5);


        Workout workout6 = new Workout();
        workout6.setGroup(true);
        workout6.setTime(LocalDateTime.of(2023, 7, 11, 20, 0));
        workout6.setTrainer(trainer1);
        workout6.setSpecialization(functional);
        workout6.setCustomers(List.of(user0));
        workoutService.save(workout6);

        Workout workout7 = new Workout();
        workout7.setGroup(true);
        workout7.setTime(LocalDateTime.of(2023, 7, 6, 16, 0));
        workout7.setTrainer(trainer1);
        workout7.setSpecialization(functional);
        workout7.setCustomers(List.of(user0));
        workoutService.save(workout7);

        Workout workout8 = new Workout();
        workout8.setGroup(true);
        workout8.setTime(LocalDateTime.of(2023, 7, 4, 16, 0));
        workout8.setTrainer(trainer1);
        workout8.setSpecialization(functional);
        workout8.setCustomers(List.of(user0));
        workoutService.save(workout8);


    }


}
