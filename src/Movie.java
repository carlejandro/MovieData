import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class Movie extends MovieCreator {
    private String movieTitle;
    private String yearReleased;
    private String Genre;
    private String Rating;

    public void getCreatorData(String fileName) throws FileNotFoundException {
        File movieListFile = new File(fileName);
        Scanner movieListFileScanner = new Scanner(movieListFile);
        String directorName = movieListFileScanner.nextLine();
        setDirectorName(directorName); //Inherited method
        String composerName = movieListFileScanner.nextLine();
        setComposerName(composerName); //Inherited method
    }
    //Default constructor
    public Movie(){

    }
    public Movie(String directorName, String composerName, String movieTitle, String yearReleased, String Genre, String Rating) throws FileNotFoundException {
        setDirectorName(directorName);
        setComposerName(composerName);
        setMovieTitle(movieTitle);
        setYearReleased(yearReleased);
        setGenre(Genre);
        setRating(Rating);
    }

    //Used in for loop. Scans the values of
    public String readValues(Scanner movieListFileScanner){
        String tempString = movieListFileScanner.nextLine();
        return tempString;
    }

    public void readToken(String tempString, ArrayList<String> movieList){
        StringTokenizer strTokenizer = new StringTokenizer(tempString, ",");
        while(strTokenizer.hasMoreTokens()){
            movieList.add(strTokenizer.nextToken().trim());
        }
    }

    ///This method makes a list, scans the strings, and fills the list with string values of the text file
    public ArrayList<String> createStringList(Scanner movieListFileScanner){
        ArrayList<String> stringMovieList = new ArrayList<>();
        while (movieListFileScanner.hasNextLine()) {
            readToken(readValues(movieListFileScanner), stringMovieList);
        }
        return stringMovieList;
    }

    //This method creates a list of movie objects from the elements of the String List
    public ArrayList<Movie> createMovieList(ArrayList<String> stringMovieList) throws FileNotFoundException {
        ArrayList<Movie> objectMovieList = new ArrayList<>();
        //These values will be incremented to navigate through the objectMovieList
        final int directorPosition = 0;
        final int composerPosition = 1;
        int movieTitlePosition = 2;
        int yearPosition = 3;
        int genrePosition = 4;
        int ratingPosition = 5;
        while (!(stringMovieList.isEmpty())){
            //Bounds checker. The movieTitlePosition is what moves initially so we need to make sure
            //It doesn't escape the index of the arrayList.
            if(movieTitlePosition >= stringMovieList.size()){
                break;
            }

            objectMovieList.add(new Movie(stringMovieList.get(directorPosition),stringMovieList.get(composerPosition),
                    stringMovieList.get(movieTitlePosition), stringMovieList.get(yearPosition), stringMovieList.get(genrePosition),
                    stringMovieList.get(ratingPosition)));
            //Increment the movie, year, genre, and rating positions to step through the list.
            movieTitlePosition += 4;
            yearPosition += 4;
            genrePosition += 4;
            ratingPosition += 4;
        }
        return objectMovieList;
    }
    //this method takes the array list of movies and filter it by genre by comparing the getter attribute to the specified genre.
    public ArrayList<Movie> filterMoviesByGenre(ArrayList<Movie> objectMovieList, String targetGenre){
        ArrayList<Movie> filteredList = new ArrayList<>();
        for (Movie movie : objectMovieList){
            if(movie.getGenre().equalsIgnoreCase(targetGenre)){
                filteredList.add(movie);
            }
        }
        return filteredList;
    }

    //Method sorts filtered list by year in ascending order using collections class and comparators
    public void sortByYear(ArrayList<Movie> filteredMovieList) {
        Collections.sort(filteredMovieList, Comparator.comparing(Movie::getYearReleased));
    }

    //This method displays the data of each genre in the filtered arrayList. Creates the filter, sort then display attributes
    public void displayFilteredData(String[] genreArray, ArrayList<Movie> objectMovieList, Movie movie) {
        //For each genre object in the list of stored genres,
        //Filter and sort the list, then append data to a string builder object
        for (int j = 0; j < genreArray.length; j++) {
            ArrayList<Movie> filteredMovies = movie.filterMoviesByGenre(objectMovieList, genreArray[j]);
            movie.sortByYear(filteredMovies);
            StringBuilder messageLog = new StringBuilder();
                //If iteration through filtered movie list isn't complete, repeat.
            if(!filteredMovies.isEmpty()) {
                //Grab the elements at the spot of filtered movies
                messageLog.append("Director:  ").append(filteredMovies.get(0).getDirectorName()).append("\n");
                messageLog.append("Composer:  ").append(filteredMovies.get(0).getComposerName()).append("\n\n");
                messageLog.append("Genre:  ").append(filteredMovies.get(0).getGenre()).append("\n\n");
                messageLog.append("Movie Title  Year Released  Rating\n");
            }
                //For each current movie in the filtered list
            for (Movie currentMovie : filteredMovies) {
                    messageLog.append(String.format("%-15s %-15s %-10s\n",
                            currentMovie.getMovieTitle(),
                            currentMovie.getYearReleased(),
                            currentMovie.getRating()));
            }
           JOptionPane.showMessageDialog(null, messageLog.toString(), "Movie Data", JOptionPane.INFORMATION_MESSAGE);
        }
        //Display information in jay option pane
    }
    public void setMovieTitle(String movieTitle){this.movieTitle = movieTitle;}
    public void setYearReleased(String yearReleased){this.yearReleased = yearReleased;}
    public void setGenre(String Genre) {this.Genre = Genre;}
    public void setRating(String Rating) {this.Rating = Rating;}


    public String getMovieTitle() {return movieTitle;}
    public String getYearReleased() {return yearReleased;}
    public String getGenre() {return Genre;}
    public String getRating() {return Rating;}

    @Override
    public String toString() {
        return "Movie{" +
                "directorName='" + getDirectorName() + '\'' +
                ", composerName='" + getComposerName() + '\'' +
                ", movieTitle='" + getMovieTitle() + '\'' +
                ", yearReleased='" + getYearReleased() + '\'' +
                ", Genre='" + getGenre() + '\'' +
                ", Rating='" + getRating() + '\'' +
                '}' + "\n";
    }

}
