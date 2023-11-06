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
        //Target Genres
        String sciFiGenre = "Sci Fi";
        String Adventure = "Adventure";
        String Drama = "Drama";
        String War = "War";
        String Romance = "Romance";
        String Thriller = "Thriller";
        String Fantasy = "Fantasy";
        String[] genreArray = {"Sci Fi", "Adventure", "Drama", "War", "Romance", "Thriller", "Fantasy" };
        Movie movie = new Movie();
        File movieListFile = new File("src/MovieListing.txt");
        Scanner movieListFileScanner = new Scanner(movieListFile);
        ArrayList<String> stringMovieList = movie.createStringList(movieListFileScanner);
        ArrayList<Movie> objectMovieList = movie.createMovieList(stringMovieList);
        movie.displayFilteredData(genreArray, objectMovieList, movie);
    }
}