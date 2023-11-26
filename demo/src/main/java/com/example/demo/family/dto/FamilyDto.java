package com.example.demo.family.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FamilyDto {
    private String familyName;
    private Long count;
}
