package spotinst_Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Element {
	private String name; // TODO Restrict the length to 32.
	private final Date creationDate;
	private SimpleDateFormat dateFormat;

	public String getName() {
		return name;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public Date getCreationDate() {
		return creationDate;
	}


	public Element(String name) {
		dateFormat = new SimpleDateFormat("dd/MM/yy");
		this.name = name;
		this.creationDate = new Date();
	}
	
	@Override
	public String toString() {
		return getName() + " " + dateFormat.format(getCreationDate());
	}
}