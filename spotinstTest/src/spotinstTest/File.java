package spotinstTest;

public class File extends Element{
	private int size;
	
	public File(String name,int size) {
		super(name);
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "File : " + name + ", " + size + ", " + sdf.format(creationDate);
	}
}
