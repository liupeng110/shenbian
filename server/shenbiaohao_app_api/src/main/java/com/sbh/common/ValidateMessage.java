<<<<<<< HEAD
package com.sbh.common;

public class ValidateMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6399670348840463721L;

	public ValidateMessage(String field, String hint) {
		this.field = field;
		this.hint = hint;
	}

	private String field;
	private String hint;

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "ValidateMessage [field=" + field + ", hint=" + hint + "]";
	}

}
=======
package com.sbh.common;

public class ValidateMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6399670348840463721L;

	public ValidateMessage(String field, String hint) {
		this.field = field;
		this.hint = hint;
	}

	private String field;
	private String hint;

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "ValidateMessage [field=" + field + ", hint=" + hint + "]";
	}

}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
