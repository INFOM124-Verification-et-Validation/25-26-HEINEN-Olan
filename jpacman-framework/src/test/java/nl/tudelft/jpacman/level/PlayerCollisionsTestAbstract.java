package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class PlayerCollisionsTestAbstract {
    protected CollisionMap collisions;
    private final PacManSprites sprites = new PacManSprites();
    private final PlayerFactory playerFactory = new PlayerFactory(sprites);
    private final GhostFactory ghostFactory = new GhostFactory(sprites);
    private final BoardFactory boardFactory = new BoardFactory(sprites);
    private Player player;
    private Pellet pellet;
    private Ghost ghost;

    @BeforeEach
    void setUp() {
        player = playerFactory.createPacMan();
        pellet = new Pellet(10,sprites.getPelletSprite());
        ghost = ghostFactory.createClyde();
        Square ground = boardFactory.createGround();
        pellet.occupy(ground);
    }

    @Test
    void PlayerCollidePellet() {
        int scoreBefore = player.getScore();
        int pelletValue = pellet.getValue();
        collisions.collide(player,pellet);
        assertThat(player.getScore()).isEqualTo(scoreBefore + pelletValue);
        assertThat(pellet.hasSquare()).isFalse();
    }

    @Test
    void PelletCollidePlayer() {
        int scoreBefore = player.getScore();
        int pelletValue = pellet.getValue();
        collisions.collide(pellet,player);
        assertThat(player.getScore()).isEqualTo(scoreBefore + pelletValue);
        assertThat(pellet.hasSquare()).isFalse();
    }

    @Test
    void PlayerCollideGhost() {
        collisions.collide(player,ghost);
        assertThat(player.isAlive()).isFalse();
    }

    @Test
    void GhostCollidePlayer() {
        collisions.collide(ghost,player);
        assertThat(player.isAlive()).isFalse();
    }

    @Test
    void GhostCollidePellet() {
        collisions.collide(ghost,pellet);
        assertThat(pellet.hasSquare()).isTrue();
    }

    @Test
    void PelletCollideGhost() {
        collisions.collide(pellet,ghost);
        assertThat(pellet.hasSquare()).isTrue();
    }
}
