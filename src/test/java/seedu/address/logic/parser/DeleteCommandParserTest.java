package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Id;
import seedu.address.model.student.IdPredicate;
import seedu.address.model.student.Name;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();
    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());

    @Test
    public void parse_validName_returnsDeleteCommand() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name studentNameToDelete = studentToDelete.getStudentName();
        assertParseSuccess(parser, " n/Alice Pauline",
                new DeleteCommand(studentNameToDelete, new NamePredicate(studentNameToDelete)));
    }

    @Test
    public void parse_validId_returnsDeleteCommand() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Id studentIdToDelete = studentToDelete.getId();
        assertParseSuccess(parser, " id/123A",
                new DeleteCommand(studentIdToDelete, new IdPredicate(studentIdToDelete)));
    }

    @Test
    public void parse_invalidName_throwsParseException() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name studentNameToDelete = studentToDelete.getStudentName();
        assertParseFailure(parser, " n/", Messages.MESSAGE_INVALID_STUDENT_NAME);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Id studentIdToDelete = studentToDelete.getId();
        assertParseFailure(parser, " id/123", Messages.MESSAGE_INVALID_STUDENT_ID);
    }
}
