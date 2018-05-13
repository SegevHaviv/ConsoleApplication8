package spotinstTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Element{
	protected String name; // TODO Restrict the length to 32.
	protected final Date creationDate;
	protected SimpleDateFormat sdf;

	public Element(String name) {
		sdf = new SimpleDateFormat("dd/MM/yy");
		this.name = name;
		this.creationDate = new Date();
	}
}
