import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JavaTest {
	
    List<Book> alreadyTreated = new ArrayList<Book>();
    Librairie isSigningUp = null;
    int nbDays = 0;
    

    public static void main(String[] args) {



    	
    }
    
    
    
    public void input(Header conf, List<Integer> scores, List<Library> librairies) {
    	
    	var nbJour = new AtomicInteger();
    	var librairieAsc = librairies.stream().sorted(Comparator.comparing(Library::getSignupTime)).collect(Collectors.toList());
    	var librairieToTreat = new ArrayList<Library>(librairieAsc);
        var alreadyTreated = new ArrayList<Book>();

    }
    
    
    public void  treatLibrary(Librairie librairie) {
        if(librairie.getSignupDays() != 0) {
            if(isSigningUp == null) {
                isSigningUp = librairie;
            }
            if(isSigningUp == librairie) {
                decrementSignUp(librairie);
            }

        }
        else {
            int nbBook = librairie.getBooksByDay();
            var books = getBooksToTreat(librairie.getBooks());
            // Scan en parallele
            for(int i = 0 ; i < librairie.getBooksByDay(); i++){

            }
        }
    }


    public void decrementSignUp(Librairie librairie) {
        librairie.setSignupDays(librairie.getSignupDays() - 1);
        // SIgn up is over so token is reaffected for a new one
        if(librairie.getSignupDays() == 0) {
            isSigningUp = null;
        }
    }

    public List<Book> getBooksToTreat(Set<Book> books) {
        // Regarde dans la liste des livres déjà traité
        var toTreat = books.stream().filter(t-> !alreadyTreated.contains(t)).collect(Collectors.toList());
        alreadyTreated.addAll(toTreat);
        return toTreat;
    }
    

}
