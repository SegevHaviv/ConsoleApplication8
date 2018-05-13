package spotinstTest;

public class Directory extends Element {
	
	public Directory(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return name + " " + sdf.format(this.creationDate);
	}
}
