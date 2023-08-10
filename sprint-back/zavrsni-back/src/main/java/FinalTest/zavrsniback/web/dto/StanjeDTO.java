package FinalTest.zavrsniback.web.dto;

import javax.validation.constraints.Positive;

public class StanjeDTO {

	@Positive(message = "Id mora biti pozitivan broj.")
	private Long id;

	private String ime = "";

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

}
