package spotinst_Test;
public class FileSystemManager {

	private final Directory root;

	public FileSystemManager() {
		root = new Directory("root");
	}

	// TODO Check the case that trying to add a file / directory to a FILE(!)
	// and not a directory.

	public boolean addFile(final String parentDirName, final String fileName, final int fileSize) {
		// Check that the file doesn't exist already and the input is valid.
		if (findElement(fileName, root) != null || parentDirName.isEmpty() || parentDirName == null
				|| fileName.isEmpty() || fileName == null){
			return false;
		}
		Element fileToAdd = new File(fileName, fileSize);

		Element parentDirectory = findElement(parentDirName, root);
		
		if (parentDirectory instanceof Directory)
			return ((Directory) parentDirectory).getSubElements().add(fileToAdd);

		return false;
	}

	public boolean addDir(final String parentDirName, final String dirName) {
		// Check that the directory doesn't exist already and the input is valid.
		if (findElement(dirName, root) != null || parentDirName.isEmpty() || parentDirName == null || dirName.isEmpty()
				|| dirName == null)
			return false;

		Element directoryToAdd = new Directory(dirName);

		Element parentDirectory = findElement(parentDirName, root);

		if (parentDirectory instanceof Directory)
			return ((Directory) parentDirectory).getSubElements().add(directoryToAdd);
		
		return false;
	}

	public boolean delete(final String name) {

		Element elementToRemove = findElement(name, root);

		if (elementToRemove == null) // Element doesn't exist.
			return false;

		Element parentDirectory = findParent(name, root);

		if(parentDirectory instanceof Directory)
			return ((Directory) parentDirectory).getSubElements().remove(elementToRemove);
		
		return false;
	}

	public void showFileSystem() {
		root.printFolder(root,0);
	}

	private Element findElement(final String desiredElementName, Directory searchingStart) {
		if (desiredElementName == "root")
			return root;

		for (Element subElement : searchingStart.getSubElements()) {
			
			if (subElement.getName().equals(desiredElementName)) // found element
				return subElement;
			else if (subElement instanceof Directory){ // directory
				Element found =  findElement(desiredElementName, (Directory) subElement);
				
				if(found != null)
					return found;
			}
		}

		return null;
	}

	private Element findParent(final String desiredElementName, Directory searchingStart) {
		if (desiredElementName == "root")
			return root;

		for (Element subElement : searchingStart.getSubElements()) {

			if (subElement.getName().equals(desiredElementName)) // found element
				return searchingStart;
			else if (subElement instanceof Directory){ // directory
				Element found =  findElement(desiredElementName, (Directory) subElement);
				
				if(found != null)
					return found;
			}
		}
		return null;
	}
}
