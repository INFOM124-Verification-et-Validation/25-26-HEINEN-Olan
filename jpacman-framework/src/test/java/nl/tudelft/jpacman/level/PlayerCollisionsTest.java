package nl.tudelft.jpacman.level;

import org.junit.jupiter.api.BeforeEach;

public class PlayerCollisionsTest extends PlayerCollisionsTestAbstract {
    @BeforeEach
    void setUpCollisions() {
        collisions = new PlayerCollisions();
    }
}
