package eu.virac.dlut.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Projekta kategorija - fundamentālais, lietišķais (ja pētniecība), administratīvais, būvniecības.
 * Saistīts ar projektu - viens (kategorija) pret daudzi (projekti).
 * 
 * Tiek izveidota attiecīga tabula DB.
 */

@Table
@Entity(name = "ProjectCategory")
@Getter @Setter @NoArgsConstructor @ToString
public class ProjectCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idProjCat")
	private int idProjCat;
	
	@NotNull
	@Column(name = "ProjCatTitle")
	private String projCatTitle;
	
	//saite ar projektu
	@OneToMany(mappedBy="projCat")
	@ToString.Exclude
    private Collection<Project> projects = new ArrayList<>();

	public ProjectCategory(String projCatTitle) {
		this.projCatTitle = projCatTitle;
	}
	
	//pievieno un izņem projektu
	public void addProjectToProjectCategory(Project p) {
		projects.add(p);
	}
					
	public void removeProjectFromProjectCategory(Project p) {
		projects.remove(p);
	}
}
