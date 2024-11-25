package hr.synverso.common.exceptions;

public enum AppError {

	NON_EXISTING_CUSTOMER("Non existing customer!"),
	JSON_PARSE_ERROR("JSON parse error"),
	BAD_REQUEST("Bad request!"),
	NON_EXISTING_CITY("Non existing city!"),
	UNRECOGNIZED_EXCEPTION("Unrecognized exception!");
	private final String desc; // error description

	AppError(String desc) {
		this.desc = desc;
	}

	public String desc() {
		return this.desc;
	}
}
