package com.esiea.tp4AOlineGame;

import com.esiea.tp4A.domain.Position;

import java.util.Set;

public interface PlayerController {

    PlayerController startNew(Game game, String playerName);

    // Connaître la position de son Rover
    Position roverPosition(String playerName);

    // Connaître la position des obstacles et joueurs adverses dans un carré 30x30 autour du joueur
    Set<Position> radar(String playerName);

    // Connaître la portée du laser
    int laserRange();

    // se déplacer (+ tirer avec son laser -> command "s")
    Position move(String command);

    // Connaître le statut de son personnage (mort ou vivant)
    Boolean alive();


}
