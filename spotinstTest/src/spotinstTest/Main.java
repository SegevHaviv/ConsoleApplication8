package spotinstTest;

public class Main {

	public static void main(String[] args) {
		
		FileSystemManager fs = new FileSystemManager();
		
		fs.addDir("root", "folder1");
		fs.addDir("folder1", "folder2");
		
		fs.addFile("root", "nne1", 11);
		fs.addFile("folder1", "nne2", 11);
		fs.addFile("folder1", "nne2", 11);
		fs.addFile("folder2", "nne3", 11);
		
		
		System.out.println("Hirerchy #1:");
		fs.showFileSystem(); // PROBLEM WITH REMOVING ROOT
		
		fs.delete("folder1");
		fs.delete("nne1");

		System.out.println("\nHirerchy #2: ");
		fs.showFileSystem();
		System.out.println();
		
		
		fs.addDir("root", "newFol");
		fs.addFile("newFol", "nameoffile", 116);
		
		
		System.out.println("\nHirerchy #3:");
		fs.showFileSystem();
	}
}
