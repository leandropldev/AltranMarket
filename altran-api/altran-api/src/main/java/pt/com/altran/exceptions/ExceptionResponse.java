package pt.com.altran.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Date timestamnp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timestamnp, String message, String details) {
		super();
		this.timestamnp = timestamnp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamnp() {
		return timestamnp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
