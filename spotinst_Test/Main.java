package spotinst_Test;
import static org.junit.Assert.assertEquals;

public class Main {

	public static void main(String[] args) {
		FileSystemManager fs = new FileSystemManager();

		assertEquals(fs.addDir("root", "Users"), true);
		assertEquals(fs.addDir("Users", "Segev"), true);
		assertEquals(fs.addDir("Users", "Segev"), false); //Directory had been created already.

		assertEquals(fs.addDir("Segev", "Photos"), true);
		assertEquals(fs.addDir("Segev", "Movies"), true);
		assertEquals(fs.addDir("UnExistedDirectory", "Movies"), false); //Parent directory doesn't exist.
		
		assertEquals(fs.addFile("Photos", "Photo1", 128),true);
		assertEquals(fs.addFile("Photos", "Photo2", 256),true);

		assertEquals(fs.addFile("Movies", "Movie1", 512),true);
		assertEquals(fs.addFile("Movies", "Movie1", 1024),false); // File exists already
		
		assertEquals(fs.addFile("Movie1", "Movie3", 2048),false); // Attempting to add file to a file instead of directory.
		
		assertEquals(fs.addFile("Segev", "Music", 4096),true);
		assertEquals(fs.addFile("root", "Another_User", 8192),true);
		
		fs.showFileSystem();

	}

}
