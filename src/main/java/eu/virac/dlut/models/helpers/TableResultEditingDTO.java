package eu.virac.dlut.models.helpers;


import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TableResultEditingDTO {

	private List<EmployeeAndHourDTO> results = new ArrayList<>();
	
	public TableResultEditingDTO(List<EmployeeAndHourDTO> results) {
		this.results = results;
	}
	
	public void addResults(EmployeeAndHourDTO data) {
		this.results.add(data);
	}
	
}
