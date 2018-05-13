package spotinstTest;

public class FileSystemManager {
	
	private final Node<Element> root;
	private final Directory rootdir;
	
	
	public FileSystemManager(){
		rootdir = new Directory("root");
		root = new Node<>(rootdir);
	}
	
	public Comparable<Element> createElementNameComparable(String toCompareTo){
		Comparable<Element> searchCriteria = new Comparable<Element>() {
			@Override
			public int compareTo(Element e) {
				if(toCompareTo == e.name)
					return 0;
				return 1;
			}
		};
		return searchCriteria;
	}

	public void addFile(String parentDirName, String fileName,int fileSize){
		File file = new File(fileName,fileSize);
		Comparable<Element> parentDirComparable = createElementNameComparable(parentDirName);
		Comparable<Element> FileNameComparable = createElementNameComparable(fileName);
		
		//check if file exists already
		if(root.findNode(FileNameComparable) != null){
			return;
		}

		Node<Element> found = root.findNode(parentDirComparable);
		
		if(found == null)
			return;
		
		found.addChild(file);
	}
	
	public void addDir(String parentDirName,String dirName){
		Directory dir = new Directory(dirName);
		
		Comparable<Element> parentDirNameComparable = createElementNameComparable(parentDirName);
		Comparable<Element> dirNameComparable = createElementNameComparable(dirName);
		

		if(root.findNode(dirNameComparable) != null){ //check if exists already
			return;
		}

		Node<Element> found = root.findNode(parentDirNameComparable);
		
		if(found == null)
			return;
		
		found.addChild(dir);
	}
	
	public void delete(String name){
		Comparable<Element> searchCriteria = new Comparable<Element>() {
			@Override
			public int compareTo(Element e) {
				if(name == e.name)
					return 0;
				return 1;
			}
		};
		Node<Element> found = root.findNode(searchCriteria);
		if(found != null){ 
	
			Node<Element> parent = found.getParent();
			if(parent != null){
				parent.removeChild(found);
				found.setParent(null);
			}
		}
	}
	
	public void showFileSystem(){
		StringBuilder sb = new StringBuilder();
		for (Node<Element> node : root) {
			sb.delete(0, sb.length());
			for (int i = 0; i < node.getLevel(); i++) {
				sb.append("  ");
			}
			System.out.println(sb.toString() + node.getData().toString());
		}
	}
}
