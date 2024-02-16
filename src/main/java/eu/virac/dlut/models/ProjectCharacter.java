package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Projekta raksturs - saimniecisks vai nesaimniecisks. 
 * Saistīts ar projektu - viens (raksturs) pret daudzi (projekts)
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "ProjectCharacter")
@Getter @Setter @NoArgsConstructor @ToString
public class ProjectCharacter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idProjChar")
	private int idProjChar;
	
	@NotNull
	@Column(name = "ProjCharTitle")
	private String projCharTitle;
	
	//saite ar projektu
	@OneToMany(mappedBy="projChar")
	@ToString.Exclude
    private Collection<Project> projects = new ArrayList<>();

	public ProjectCharacter(String projCharTitle) {
		this.projCharTitle = projCharTitle;
	}
	
	//pievieno un izņem projektu
	public void addProjectToProjectCharacter(Project p) {
		projects.add(p);
	}
					
	public void removeProjectFromProjectCharacter(Project p) {
		projects.remove(p);
	}

}
