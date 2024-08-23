package com.example.stock.domain.brand.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BrandCreateCommand {
    private String name;
    private String description;
}
