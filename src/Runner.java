import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;



public class Runner extends SimpleFileVisitor<Path>
{
    @Override
    public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs)
    {
    	if (attrs.size()<1000000)
    	{
    		try
			{
				Files.delete(dir);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		return FileVisitResult.CONTINUE;
    	}
    	else
    	{
    		return FileVisitResult.TERMINATE;
    	}
    }
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException e)
        throws IOException
    {
    	Path filmyDir = Paths.get("C:/Users/Ola/Filmy");
    	if (dir.equals(filmyDir))
    	{
    		return FileVisitResult.TERMINATE;
    	}
    	else
    	{
    		if (e == null) {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            } else {
                // directory iteration failed
                throw e;
            }
    		
    	}
    }

	public static void main(String[] args)
	{
		
		Path directory = Paths.get ("C:/Users/Ola/Filmy");		
		
		Runner visitor = new Runner();
	
		//directory.forEach(action);
		try
		{
			Files.walkFileTree(directory, visitor);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
