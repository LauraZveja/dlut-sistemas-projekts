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
public class HoursInMonthDTO {

    private int idHoursInMonth;

    @NotNull
    private int year;

    @NotNull
    private int month;

    @NotNull
    private double hoursInMonth;

    public HoursInMonthDTO(int idHoursInMonth, int year, int month, double hoursInMonth) {
        this.idHoursInMonth = idHoursInMonth;
        this.year = year;
        this.month = month;
        this.hoursInMonth = hoursInMonth;
    }

}
