package FinalTest.zavrsniback.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

public class ZadatakDTO {

	@Positive(message = "Id mora biti pozitivan broj.")
	private Long id;

	@Nullable
	@NotBlank
	@Length(max = 40)
	private String ime = "";

	private String zaduzeni = "";

	@Min(value = 0)
	@Max(value = 20)
	private int bodovi;

	private Long sprintId;

	private String nazivSprinta = "";

	private Long stanjeId;

	private String nazivStanje = "";

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
	 * @return the sprintId
	 */
	public Long getSprintId() {
		return sprintId;
	}

	/**
	 * @param sprintId the sprintId to set
	 */
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	/**
	 * @return the nazivSprinta
	 */
	public String getNazivSprinta() {
		return nazivSprinta;
	}

	/**
	 * @param nazivSprinta the nazivSprinta to set
	 */
	public void setNazivSprinta(String nazivSprinta) {
		this.nazivSprinta = nazivSprinta;
	}

	/**
	 * @return the stanjeId
	 */
	public Long getStanjeId() {
		return stanjeId;
	}

	/**
	 * @param stanjeId the stanjeId to set
	 */
	public void setStanjeId(Long stanjeId) {
		this.stanjeId = stanjeId;
	}

	/**
	 * @return the nazivStanje
	 */
	public String getNazivStanje() {
		return nazivStanje;
	}

	/**
	 * @param nazivStanje the nazivStanje to set
	 */
	public void setNazivStanje(String nazivStanje) {
		this.nazivStanje = nazivStanje;
	}

}
