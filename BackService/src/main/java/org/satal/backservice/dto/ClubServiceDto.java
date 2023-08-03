package org.satal.backservice.dto;

import lombok.Data;
import org.satal.backservice.entities.gridClasses.ClubService;

@Data
public class ClubServiceDto {
    private Long id;
    private String title;
    private String description;
    private String ico;

    public ClubServiceDto(ClubService clubService) {
        this.id = clubService.getId();
        this.title = clubService.getTitle();
        this.description = clubService.getDescription();
        this.ico = clubService.getIco();
    }
}
