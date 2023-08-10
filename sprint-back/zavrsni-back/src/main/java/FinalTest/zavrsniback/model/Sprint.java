package FinalTest.zavrsniback.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String ime = "";

	@Column
	private String ukupnoBodova = "";

	@OneToMany(mappedBy = "sprint", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Zadatak> zadaci = new ArrayList<>();

	public Sprint() {
	}

	public Sprint(String ime, String ukupnoBodova, List<Zadatak> zadaci) {
		this.ime = ime;
		this.ukupnoBodova = ukupnoBodova;
		this.zadaci = zadaci;
	}

	public Sprint(Long id, String ime, String ukupnoBodova, List<Zadatak> zadaci) {
		this.id = id;
		this.ime = ime;
		this.ukupnoBodova = ukupnoBodova;
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
	 * @return the ukupnoBodova
	 */
	public String getUkupnoBodova() {
		return ukupnoBodova;
	}

	/**
	 * @param ukupnoBodova the ukupnoBodova to set
	 */
	public void setUkupnoBodova(String ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
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
	
	public void obrisiZadatak(Zadatak stariZadatak) {
		for (Zadatak zadatak : this.zadaci) {
			if (zadatak.getId() == stariZadatak.getId()) {
				this.zadaci.remove(zadatak);
				int noviBodovi = Integer.parseInt(this.getUkupnoBodova()) - zadatak.getBodovi();
				this.setUkupnoBodova(String.valueOf(noviBodovi));
				return;
			}
		}
	}
	

	public void dodajZadatak(Zadatak zadatak) {
		this.zadaci.add(zadatak);
		int noviBodovi = Integer.parseInt(this.getUkupnoBodova()) + zadatak.getBodovi();
		this.setUkupnoBodova(String.valueOf(noviBodovi));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sprint)) {
			return false;
		}
		Sprint other = (Sprint) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Sprint [id=" + id + ", ime=" + ime + ", ukupnoBodova=" + ukupnoBodova + ", zadaci=" + zadaci + "]";
	}

}
