package andy.lopez;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Menu {
    private final JFrame frame = new JFrame("Gestion des filmes");
    private final List<Movie> Movies = new ArrayList<>();
    private final GestionFichier gestionFichier = new GestionFichier("movies.txt");

    public Menu() {
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chargerMoviesDepuisFichier();
    }

    public void mainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));
        frame.add(panel);

        JButton ajout = new JButton("Ajouter un filme");
        JButton affichage = new JButton("Afficher les filmes");
        JButton suppression = new JButton("Supprimer un filme");
        JButton quitter = new JButton("Quitter");

        panel.add(ajout);
        panel.add(affichage);
        panel.add(suppression);
        panel.add(quitter);

        ajout.addActionListener(e -> ajouterMovie());
        affichage.addActionListener(e -> afficherMovie());
        suppression.addActionListener(e -> supprimerMovie());
        quitter.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void ajouterMovie() {
        String name = JOptionPane.showInputDialog(frame, "Entrez le nom du filme :");
        String time = JOptionPane.showInputDialog(frame, "la durée du filme :");

        if (name != null && time != null && !name.isEmpty() && !time.isEmpty()) {
        	Movie movie = new Movie(name, time);
            Movies.add(movie);
            gestionFichier.writeLine(name + "," + time);
            JOptionPane.showMessageDialog(frame, "Filme ajouté avec succès !");
        } else {
            JOptionPane.showMessageDialog(frame, "Les informations sont invalides.");
        }
    }

    private void afficherMovie() {
        if (Movies.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aucun filme dans la liste.");
            return;
        }

        StringBuilder sb = new StringBuilder("Liste des filmes :\n");
        for (Movie movie : Movies) {
            sb.append(movie.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, sb.toString());
    }

    private void supprimerMovie() {

        String name = JOptionPane.showInputDialog(frame, "Entrez le nom du filme à supprimer :");
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nom invalide.");
            return;
        }

        Movie aSupprimer = null;
        for (Movie movie : Movies) {
            if (movie.getName().equalsIgnoreCase(name)) { 
                aSupprimer = movie;
                break;
            }
        }

        if (aSupprimer != null) {
            Movies.remove(aSupprimer); 
            sauvegarderListeDansFichier();  
            JOptionPane.showMessageDialog(frame, "Film supprimé avec succès !");
        } else {
            JOptionPane.showMessageDialog(frame, "Aucun film trouvé avec ce nom.");
        }
    }
    
    private void sauvegarderListeDansFichier() {
        List<String> lines = new ArrayList<>();
        
        for (Movie movie : Movies) {
            lines.add(movie.getName() + "," + movie.getTime());
        }

        gestionFichier.writeAllLines(lines);
    }

    private void chargerMoviesDepuisFichier() {
        List<String> lines = gestionFichier.readAllLines();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                Movies.add(new Movie(parts[0], parts[1]));
            }
        }
    }
}