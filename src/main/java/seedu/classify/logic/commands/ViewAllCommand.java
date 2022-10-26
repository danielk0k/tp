package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.classify.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.classify.model.Model;

/**
 * Lists all student records.
 */
public class ViewAllCommand extends Command {

    public static final String COMMAND_WORD = "viewAll";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
