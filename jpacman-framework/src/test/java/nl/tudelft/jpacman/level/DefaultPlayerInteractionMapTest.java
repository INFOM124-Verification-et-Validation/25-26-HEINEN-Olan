package nl.tudelft.jpacman.level;

import org.junit.jupiter.api.BeforeEach;

public class DefaultPlayerInteractionMapTest extends PlayerCollisionsTestAbstract {
    @BeforeEach
    void setUpCollisions() {
        collisions = new DefaultPlayerInteractionMap();
    }
}
