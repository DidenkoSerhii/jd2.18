import org.example.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createNote() {
        Note note = new Note();
        note.setTitle("Test note");
        note.setContent("This is a test note");

        ResponseEntity<Note> response = restTemplate.postForEntity("/api/notes", note, Note.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getNote() {
        ResponseEntity<Note> response = restTemplate.getForEntity("/api/notes/1", Note.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateNote() {
        Note note = new Note();
        note.setTitle("Updated note");
        note.setContent("This is an updated note");

        ResponseEntity<Note> response = restTemplate.exchange("/api/notes/1", HttpMethod.PUT, new HttpEntity<>(note), Note.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteNote() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/notes/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}