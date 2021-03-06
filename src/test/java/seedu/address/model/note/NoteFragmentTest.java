package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNotes.PIPELINE;
import static seedu.address.testutil.TypicalNotes.SAMPLE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;

public class NoteFragmentTest {

    @Test
    public void toString_format_success() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("sampleTag"));
        NoteFragment noteFragment = new NoteFragment(new Title("this is a title"), new Content("this is a content"),
                tags);
        assertEquals(noteFragment.toString(), "\n\tTitle: this is a title\n\tContent: this is a content"
                + "\n\tTags: [sampletag]");
    }

    @Test
    public void requireNonNull_noTitleProvided_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new NoteFragment(new Title("title"),
                new Content(""), null));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        NoteFragment noteFragment = new NoteBuilder().buildFragment();
        assertThrows(UnsupportedOperationException.class, () -> noteFragment.getTags().remove(0));
    }

    @Test
    public void isSameNote() {
        // same object -> returns true
        assertTrue(SAMPLE.isSameNote(SAMPLE));

        // null -> returns false
        assertFalse(SAMPLE.isSameNote(null));

        // different title -> returns false
        NoteFragment differentTitle = new NoteBuilder(SAMPLE).withTitle("Different Sample Title").buildFragment();
        assertFalse(SAMPLE.isSameNote(differentTitle));

        // same title -> returns true
        NoteFragment sameTitle = new NoteBuilder(SAMPLE).withTitle("Sample Title").buildFragment();
        assertTrue(SAMPLE.isSameNote(sameTitle));
    }

    @Test
    public void equals() {
        // same values -> returns true
        NoteFragment sampleCopy = new NoteBuilder(SAMPLE).buildFragment();
        assertTrue(SAMPLE.equals(sampleCopy));

        // same object -> returns true
        assertTrue(sampleCopy.equals(sampleCopy));

        // null -> returns false
        assertFalse(sampleCopy.equals(null));

        // different type -> returns false
        assertFalse(sampleCopy.equals(5));

        // different noteFragment -> returns false
        assertFalse(sampleCopy.equals(PIPELINE));

        // different name -> returns false
        NoteFragment editedNoteFragment = new NoteBuilder(SAMPLE).withTitle("Different Sample Title").buildFragment();
        assertFalse(sampleCopy.equals(editedNoteFragment));
    }
}
