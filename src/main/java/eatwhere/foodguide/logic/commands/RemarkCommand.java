package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.commons.util.CollectionUtil.requireAllNonNull;
import static eatwhere.foodguide.model.Model.PREDICATE_SHOW_ALL_EATERIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Remark;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the eatery identified "
            + "by the index number used in the latest list. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK  + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Student discounts available.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Eatery: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Eatery: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code eateryToEdit}.
     */
    private String generateSuccessMessage(Eatery eateryToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, eateryToEdit);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Eatery> lastShownList = model.getFilteredEateryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
        }

        Eatery personToEdit = lastShownList.get(index.getZeroBased());
        Eatery editedPerson = new Eatery(
                personToEdit.getName(), personToEdit.getPrice(), personToEdit.getCuisine(),
                personToEdit.getLocation(), personToEdit.getTags(), remark);

        model.setEatery(personToEdit, editedPerson);
        model.updateFilteredEateryList(PREDICATE_SHOW_ALL_EATERIES);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }

}