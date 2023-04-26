package com.rodolpho.projetothais.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NurseResponse {
    private List<NurseDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
    private long more10Years; 
    private long genderFeminino;
    private int genderMasculino;
    private double mediaFeminina;

}
