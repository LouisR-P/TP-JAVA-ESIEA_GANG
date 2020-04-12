package com.esiea.tp4AOnlineGame;

import com.esiea.tp4A.domain.Position;

import java.util.Set;

public interface PlayerController {

    PlayerController startNew(Game game, String playerName);

    // Connaître la position de son Rover
    Position roverPosition();

    // Connaître la position des obstacles et joueurs adverses dans un carré 30x30 autour du joueur
    Set<Position> radar(String playerName);

    // Connaître la portée du laser
    int laserRange(String playerName);

    // se déplacer (+ tirer avec son laser -> command "s")
    Position move(String playerName, String command);

    // Connaître le statut de son personnage (mort ou vivant)
    Boolean alive(String playerName);


}
