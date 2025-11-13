package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.within;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.time.*;

public class AutoAssignerTest {

    private Map<ZonedDateTime, Integer> map, map2;
    private ZonedDateTime date1;
    private Workshop workshop1, workshop2;
    Student student1, student2;
    List<Student> students;
    List<Workshop> workshops;

    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }

    @Before
    public void setup() {
        map = new HashMap<ZonedDateTime, Integer>();
        map2 = new HashMap<ZonedDateTime, Integer>();
        date1 = date(2025,11,4,10,0);
        student1 = new Student(1,"a","email1");
        student2 = new Student(2,"b","email2");
    }

    @Test
    public void testAssignNoSpot() {
        map.put(date1,0);
        workshop1 = new Workshop(1, "w1", map);
        workshops = Arrays.asList(workshop1);
        students = Arrays.asList(student1);

        AutoAssigner assigner = new AutoAssigner();
        AssignmentsLogger logger = assigner.assign(students,workshops);
        assertThat(logger.getAssignments()).isEmpty();
    }

    @Test
    public void testAssignSpot() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);
        workshops = Arrays.asList(workshop1);
        students = Arrays.asList(student1);

        AutoAssigner assigner = new AutoAssigner();
        AssignmentsLogger logger = assigner.assign(students,workshops);
        assertThat(logger.getAssignments()).isNotEmpty();
    }

    @Test
    public void testAssign2But1Spot() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);
        workshops = Arrays.asList(workshop1);
        students = Arrays.asList(student1,student2);

        AutoAssigner assigner = new AutoAssigner();
        AssignmentsLogger logger = assigner.assign(students,workshops);
        assertThat(logger.getAssignments()).isNotEmpty();
        assertThat(logger.getErrors()).isNotEmpty();
    }

    @Test
    public void testAssign2Spots() {
        map.put(date1,1);
        map2.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);
        workshop2 = new Workshop(2, "w2", map2);
        workshops = Arrays.asList(workshop1,workshop2);
        students = Arrays.asList(student1,student2);

        AutoAssigner assigner = new AutoAssigner();
        AssignmentsLogger logger = assigner.assign(students,workshops);
        assertThat(logger.getErrors()).isNotEmpty();
    }//pas utile

    @Test
    public void testWorkshopGetSpots() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);

        assertThat(workshop1.getSpotsPerDate()).isEqualTo(map);
    }

    @Test
    public void testWorkshopGetName() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);

        assertThat(workshop1.getName()).isEqualTo("w1");
    }

    @Test
    public void testWorkshopGetId() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);

        assertThat(workshop1.getId()).isEqualTo(1);
    }

    @Test
    public void testWorkshopHasDate() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);

        assertThat(workshop1.hasAvailableDate()).isTrue();
    }

    @Test
    public void testWorkshopHasNoDate() {
        map.put(date1,0);
        workshop1 = new Workshop(1, "w1", map);

        assertThat(workshop1.hasAvailableDate()).isFalse();
    }

    @Test
    public void testWorkshopGetDate() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);

        assertThat(workshop1.getNextAvailableDate()).isEqualTo(date1);
    }

    @Test
    public void testWorkshopTakeSpot() {
        map.put(date1,1);
        workshop1 = new Workshop(1, "w1", map);
        workshop1.takeASpot(date1);
        assertThat(workshop1.hasAvailableDate()).isFalse();
    }
}
