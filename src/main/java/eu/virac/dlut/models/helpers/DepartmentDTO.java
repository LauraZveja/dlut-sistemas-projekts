package eu.virac.dlut.models.helpers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentDTO {

    public int idDepartment;

    @NotNull
    public String title;

    public String abbreviation;

    public DepartmentDTO(int idDepartment, String title, String abbreviation) {
        this.idDepartment = idDepartment;
        this.title = title;
        this.abbreviation = abbreviation;
    }
}
