package eu.virac.dlut.models.helpers;

import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    @NotNull
    //viens vārds, divi vārdi ar defisi, divi vārdi ar atstarpi
    @Pattern(regexp = "^\\p{Lu}{1}\\p{Ll}+( \\p{Lu}{1}\\p{Ll}+|-\\p{Lu}{1}\\p{Ll}+)?$")
    private String name;

    @NotNull
    //viens vārds, divi vārdi ar defisi, divi vārdi ar atstarpi
    @Pattern(regexp = "^\\p{Lu}{1}\\p{Ll}+( \\p{Lu}{1}\\p{Ll}+|-\\p{Lu}{1}\\p{Ll}+)?$")
    private String surname;

    @NotNull
    private String position;

    @NotNull
    private boolean isElected;

    @NotNull
    private String workContractNoDate;

    @NotNull
    private String departmentName;

    private int idEmployee;

    public EmployeeDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public EmployeeDTO(int idEmployee, String name, String surname, String position, boolean isElected, String workContractNoAndDate, String departmentName) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.isElected = isElected;
        this.workContractNoDate = workContractNoAndDate;
        this.departmentName = departmentName;
    }

}
