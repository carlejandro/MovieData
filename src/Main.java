import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        String directorName;
//        String composerName;
//        Movie movie = new Movie();
//        movie.getCreatorData("src/MovieListing.txt");
//        directorName = movie.getDirectorName();
//        composerName = movie.getComposerName();
//
//        System.out.println("Director: " + directorName);
//        System.out.println("Composer: " + composerName);
        //Add filter variables stop here
        Movie movie = new Movie();
        File movieListFile = new File("src/MovieListing.txt");
        Scanner movieListFileScanner = new Scanner(movieListFile);
        ArrayList<String> stringMovieList = movie.createStringList(movieListFileScanner);
        ArrayList<Movie> objectMovieList = movie.createMovieList(stringMovieList);
        System.out.println(objectMovieList);


    }
}