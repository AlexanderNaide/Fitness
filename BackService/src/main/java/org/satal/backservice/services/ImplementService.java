package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.Maintenance;
import org.satal.backservice.entities.users.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImplementService {

    private final RoleService roleService;

    private final SpecialisationService specialisationService;

    private final UserService userService;

    private final SeasonTicketService seasonTicketService;

    private final MaintenanceService maintenanceService;

    public void init(){
        Role superUserRole = new Role();
        superUserRole.setRole("super");
        roleService.save(superUserRole);

        Role adminRole = new Role();
        adminRole.setRole("admin");
        roleService.save(adminRole);

        Role trainerRole = new Role();
        trainerRole.setRole("trainer");
        roleService.save(trainerRole);

        Role userRole = new Role();
        userRole.setRole("user");
        roleService.save(userRole);

        Specialization functional = new Specialization();
        functional.setSpecialization("functional");
        specialisationService.save(functional);

        Specialization water = new Specialization();
        water.setSpecialization("water");
        specialisationService.save(water);

        Specialization group = new Specialization();
        group.setSpecialization("group");
        specialisationService.save(group);

        Specialization kids = new Specialization();
        kids.setSpecialization("kids");
        specialisationService.save(kids);

        Specialization massage = new Specialization();
        massage.setSpecialization("massage");
        specialisationService.save(massage);

        User superUser = new User();
        superUser.setLogin("super");
        superUser.setPassword("123");
        superUser.setRoles(List.of(superUserRole));
        superUser.setName("Super");
        superUser.setSurname("Admin");
        superUser.setBackground("../images/contact.jpg");
        superUser.setAvatar("../images/gallery_5.jpg");
        userService.save(superUser);

        User trainer1 = new User();
        trainer1.setLogin("tr1");
        trainer1.setPassword("123");
        trainer1.setRoles(List.of(trainerRole));
        trainer1.setName("Trainer1");
        trainer1.setSurname("***");
        trainer1.setSpecialization(functional);
        userService.save(trainer1);

        User trainer2 = new User();
        trainer2.setLogin("tr2");
        trainer2.setPassword("123");
        trainer2.setRoles(List.of(trainerRole));
        trainer2.setName("Trainer2");
        trainer2.setSurname("***");
        trainer2.setSpecialization(water);
        userService.save(trainer2);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("123");
        admin.setRoles(List.of(adminRole));
        admin.setName("Admin");
        admin.setSurname("***");
        userService.save(admin);

        User user = new User();
        user.setLogin("user");
        user.setPassword("123");
        user.setRoles(List.of(userRole));
        user.setName("User");
        user.setSurname("***");
        userService.save(user);

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

        Maintenance functionalMaintenance = new Maintenance();
        functionalMaintenance.setTitle("ТРЕНАЖЕРНЫЙ ЗАЛ, КАРДИОТЕАТРЫ");
        functionalMaintenance.setIco("assets/images/services/gum.png");
        functionalMaintenance.setDescription("В тренажерных залах нашего клуба установлено профессиональное оборудование ведущих мировых производителей.");
        maintenanceService.save(functionalMaintenance);

        Maintenance waterMaintenance = new Maintenance();
        waterMaintenance.setTitle("ТРИ БАССЕЙНА");
        waterMaintenance.setIco("assets/images/services/pool.png");
        waterMaintenance.setDescription("Просторная аквазона нашего фитнес клуба включает три бассейна: тренировочный, детский, развлекательный бассейн с гидромассажными линиями и джакузи, а также банный комплекс, суши-бар, зоны отдыха и массажный кабинет.");
        maintenanceService.save(waterMaintenance);

        Maintenance groupMaintenance = new Maintenance();
        groupMaintenance.setTitle("ГРУППОВЫЕ ПРОГРАММЫ");
        groupMaintenance.setIco("assets/images/services/group.png");
        groupMaintenance.setDescription("Групповые программы в фитнес клубе «Три Океана» - это прекрасная физическая форма и отличная компания! Здесь никогда не бывает скучно!");
        maintenanceService.save(groupMaintenance);

        Maintenance personalMaintenance = new Maintenance();
        personalMaintenance.setTitle("ПЕРСОНАЛЬНЫЙ ТРЕНИНГ");
        personalMaintenance.setIco("assets/images/services/personal.png");
        personalMaintenance.setDescription("Персональный тренинг используется в любом направлении фитнеса: плавание и аквааэробика, тренажерный зал, аэробика, Mind Body, единоборства, танцевальные направления.");
        maintenanceService.save(personalMaintenance);

    }


}
