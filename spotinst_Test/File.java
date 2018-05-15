package spotinst_Test;

public class File extends Element{

	private Integer size;

	public File(String name,int size) {
		super(name);
		this.size = size;
	}
	
	@Override
	public String toString(){
		return super.toString() + " " + size;
		
	}
}
