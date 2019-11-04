package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.cheatsheet.CheatSheet;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class StudyBuddyProTest {

    private final StudyBuddyPro studyBuddyPro = new StudyBuddyPro();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studyBuddyPro.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studyBuddyPro.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        StudyBuddyPro newData = getTypicalAddressBook();
        studyBuddyPro.resetData(newData);
        assertEquals(newData, studyBuddyPro);
    }

    /* To fix
    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Flashcard> newFlashcards = Arrays.asList(MATH_ONE, CS_ONE);
        AddressBookStub newData = new AddressBookStub(newPersons, newFlashcards);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }
    */

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studyBuddyPro.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(studyBuddyPro.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        studyBuddyPro.addPerson(ALICE);
        assertTrue(studyBuddyPro.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        studyBuddyPro.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(studyBuddyPro.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studyBuddyPro.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons and flashcard list can violate interface constraints.
     */
    private static class StudyBuddyProStub implements ReadOnlyStudyBuddyPro {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();
        private final ObservableList<Note> notes = FXCollections.observableArrayList();
        private final ObservableList<CheatSheet> cheatSheets = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        StudyBuddyProStub(Collection<Person> persons, Collection<Flashcard> flashcards) {
            this.persons.setAll(persons);
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<CheatSheet> getCheatSheetList() {
            return cheatSheets;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }

        @Override
        public ObservableList<Note> getNoteList() {
            return notes;
        }
    }
}
