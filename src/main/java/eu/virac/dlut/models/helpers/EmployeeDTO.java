package eu.virac.dlut.models.helpers;

import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
