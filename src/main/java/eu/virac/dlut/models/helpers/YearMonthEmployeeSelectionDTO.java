package eu.virac.dlut.models.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearMonthEmployeeSelectionDTO {

    @NotNull
    private List<String> years;

    @NotNull
    private List<String> months;

    @NotNull
    private List<EmployeeDTO> employees;
}
