package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.LocationContainsKeywordsPredicate;

/**
 * Finds and lists all eateries in food guide whose tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindLocationCommand extends Command {

    public static final String COMMAND_WORD = "findLocation";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all eateries whose location matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " mala";

    private final LocationContainsKeywordsPredicate predicate;

    public FindLocationCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEateryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EATERIES_LISTED_OVERVIEW, model.getFilteredEateryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindLocationCommand // instanceof handles nulls
                && predicate.equals(((FindLocationCommand) other).predicate)); // state check
    }
}
