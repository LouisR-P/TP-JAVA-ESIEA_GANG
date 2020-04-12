package java.tp4AOlineGame;

import com.esiea.tp4A.domain.Position;

import java.util.Set;

public interface Game {

    // Connaître la position de son Rover
    Position roverPosition(String playerName);

    // Connaître la position des obstacles et joueurs adverses dans un carré 30x30 autour du joueur
    Set<Position> radar(String playerName);

    // Connaître la portée du laser
    int laserRange(String playerName);

    // se déplacer (+ tirer avec son laser -> command "s")
    Position move(String playerName, String command);

    // Connaître le statut de son personnage (mort ou vivant)
    Boolean alive(String playerName);

    // Ajouter un joueur dans la partie à l'aide du PlayerController
    PlayerController addPlayer(String playerName);

}
