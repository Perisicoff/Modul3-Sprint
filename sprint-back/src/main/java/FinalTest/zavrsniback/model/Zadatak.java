package FinalTest.zavrsniback.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Zadatak {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String ime = "";

	@Column(nullable = false)
	private String zaduzeni = "";

	@Column
	private int bodovi;

	@ManyToOne
	private Sprint sprint;

	@ManyToOne
	private Stanje stanje;

	public Zadatak() {
	}

	public Zadatak(String ime, String zaduzeni, int bodovi, Sprint sprint, Stanje stanje) {
		this.ime = ime;
		this.zaduzeni = zaduzeni;
		this.bodovi = bodovi;
		this.sprint = sprint;
		this.stanje = stanje;
	}

	public Zadatak(Long id, String ime, String zaduzeni, int bodovi, Sprint sprint, Stanje stanje) {
		this.id = id;
		this.ime = ime;
		this.zaduzeni = zaduzeni;
		this.bodovi = bodovi;
		this.sprint = sprint;
		this.stanje = stanje;
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
	 * @return the zaduzeni
	 */
	public String getZaduzeni() {
		return zaduzeni;
	}

	/**
	 * @param zaduzeni the zaduzeni to set
	 */
	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}

	/**
	 * @return the bodovi
	 */
	public int getBodovi() {
		return bodovi;
	}

	/**
	 * @param bodovi the bodovi to set
	 */
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	/**
	 * @return the sprint
	 */
	public Sprint getSprint() {
		return sprint;
	}

	/**
	 * @param sprint the sprint to set
	 */
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	/**
	 * @return the stanje
	 */
	public Stanje getStanje() {
		return stanje;
	}

	/**
	 * @param stanje the stanje to set
	 */
	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
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
		if (!(obj instanceof Zadatak)) {
			return false;
		}
		Zadatak other = (Zadatak) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Zadatak [id=" + id + ", ime=" + ime + ", zaduzeni=" + zaduzeni + ", bodovi=" + bodovi + ", sprint="
				+ sprint + ", stanje=" + stanje + "]";
	}

}
