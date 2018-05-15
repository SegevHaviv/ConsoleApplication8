package spotinst_Test;
import java.util.ArrayList;
import java.util.List;

public class Directory extends Element {
	private List<Element> subElements;

	public Directory(String name) {
		super(name);
		subElements = new ArrayList<>();
	}

	public List<Element> getSubElements() {
		return subElements;
	}

	public void setSubElements(ArrayList<Element> subElements) {
		this.subElements = subElements;
	}

	public void printFolder(Directory dir, int indent) {
		System.out.println(createIndent(indent) + dir.toString());

			for (Element e : dir.getSubElements()) {
				if (e instanceof Directory) {
					printFolder((Directory) e, indent + 1);
				} else
					System.out.println(createIndent(indent + 1) + e.toString());
			}
	}

	private String createIndent(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			sb.append('-');
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return super.toString();
	}

}