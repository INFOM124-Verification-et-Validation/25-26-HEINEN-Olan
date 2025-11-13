package nl.tudelft.jpacman.npc.ghost;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.*;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClydeTest {
    private Clyde clyde;
    private final PacManSprites sprites = new PacManSprites();
    private final GhostFactory ghostFactory = new GhostFactory(sprites);
    private final PlayerFactory playerFactory = new PlayerFactory(sprites);
    private final BoardFactory boardFactory = new BoardFactory(sprites);
    private final LevelFactory levelFactory = new LevelFactory(sprites,ghostFactory);
    private final GhostMapParser ghostMapParser = new GhostMapParser(levelFactory,boardFactory,ghostFactory);

    @BeforeEach
    void setUp() {
    }

    @Test
    void noPacman() {
        char[][] map = {"############".toCharArray(), "#P         C#".toCharArray(), "############".toCharArray()};
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class,ghostMapParser.parseMap(map).getBoard());

        assert clyde != null;
        assert clyde.hasSquare();
        Optional<Direction> nextMove = clyde.nextAiMove();
        assertThat(nextMove.isEmpty()).isEqualTo(true);
    }

    @Test
    void noClyde() {
        char[][] map = {"############".toCharArray(), "#P          #".toCharArray(), "############".toCharArray()};
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class,ghostMapParser.parseMap(map).getBoard());

        assertThat(clyde).isNull();
    }

    @Test
    void freeWayLongRange() {
        List<String> map = Arrays.asList(
            "#############",
            "P           C",
            "###########  "
        );
        Level lvl = ghostMapParser.parseMap(map);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, lvl.getBoard());
        Player player = playerFactory.createPacMan();
        lvl.registerPlayer(player);
        assert clyde != null;
        Optional<Direction> nextMove = clyde.nextAiMove();
        System.out.println(nextMove);
        assertThat(nextMove.get()).isEqualTo(Direction.valueOf("WEST"));
    }

    @Test
    void freeWayShortRange() {
        List<String> map = Arrays.asList(
            "#############",
            "######P    C ",
            "###########  "
        );
        Level lvl = ghostMapParser.parseMap(map);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, lvl.getBoard());
        Player player = playerFactory.createPacMan();
        lvl.registerPlayer(player);
        assert clyde != null;
        Optional<Direction> nextMove = clyde.nextAiMove();
        assertThat(nextMove.get()).isEqualTo(Direction.valueOf("EAST"));
    }
}
