package FinalTest.zavrsniback.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column( nullable = false, unique = true)
	private String ime = "";

	@OneToMany(mappedBy = "stanje", cascade = CascadeType.ALL)
	private List<Zadatak> zadaci = new ArrayList<>();

	public Stanje() {
	}

	public Stanje(String ime, List<Zadatak> zadaci) {
		this.ime = ime;
		this.zadaci = zadaci;
	}

	public Stanje(Long id, String ime, List<Zadatak> zadaci) {
		this.id = id;
		this.ime = ime;
		this.zadaci = zadaci;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ime
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * @param ime the ime to set
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * @return the zadaci
	 */
	public List<Zadatak> getZadaci() {
		return zadaci;
	}

	/**
	 * @param zadaci the zadaci to set
	 */
	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Stanje)) {
			return false;
		}
		Stanje other = (Stanje) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Stanje [id=" + id + ", ime=" + ime + ", zadaci=" + zadaci + "]";
	}

}
