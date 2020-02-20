import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JavaTest {
	
    List<Book> alreadyTreated = new ArrayList<Book>();
    Librairie isSigningUp = null;
    int nbDays = 0;
    

    public static void main(String[] args) {



    	
    }
    
    
    
    public List<Librairie> input(Header conf, List<Librairie> librairies) {
    	
    	var nbJour = new AtomicInteger(1);
    	var librairieAsc = librairies.stream().sorted(Comparator.comparing(Librairie::getSignupDays)).collect(Collectors.toList());
    	var librairieToTreat = new ArrayList<Librairie>(librairieAsc);

        // Parealle et retrait livres

        while(nbJour.get() <= conf.getNbDaysTotal()) {
            librairieToTreat.forEach(t -> treatLibrary(t));
            nbJour.addAndGet(1);
        }

        removeT(librairieToTreat);
        return librairieToTreat;
    }


    private void removeT(List<Librairie> librairies) {
        librairies.forEach(t -> {
            var books = t.getBooks();
            books.removeAll(alreadyTreated);
        });
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
            var books = getBooksToTreat(librairie);
        }
    }


    public void decrementSignUp(Librairie librairie) {
        librairie.setSignupDays(librairie.getSignupDays() - 1);
        // SIgn up is over so token is reaffected for a new one
        if(librairie.getSignupDays() == 0) {
            isSigningUp = null;
        }
    }

    public List<Book> getBooksToTreat(Librairie librairie) {
        Set<Book> books = librairie.getBooks();
        // Regarde dans la liste des livres déjà traité
        var toTreat = books.stream().filter(t-> !alreadyTreated.contains(t)).limit(librairie.getBooksByDay()).collect(Collectors.toList());
        alreadyTreated.addAll(toTreat);
        return toTreat;
    }
    

}
