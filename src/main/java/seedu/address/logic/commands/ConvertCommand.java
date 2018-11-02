package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Transaction;

/**
 * Converts multiple amounts in different currencies to the base currency.
 */
public class ConvertCommand extends Command {
    public static final String COMMAND_WORD = "convert";
    public static final String COMMAND_ALIAS = "con";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Converts given amounts from their respective foreign currencies to Singapore Dollars\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Amounts converted in all transactions!!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Transaction> lastShownList = model.getFilteredTransactionList();
        try {
            for (Transaction transactionToEdit : lastShownList) {
                Amount convertedAmount = Amount.convertCurrency(transactionToEdit.getAmount());
                Transaction editedTransaction = Transaction.copy(transactionToEdit);
                editedTransaction.setAmount(convertedAmount);
                model.updateTransaction(transactionToEdit, editedTransaction);
            }
        } catch (IOException ex) {
            throw new CommandException("Error while reading values from the Currency Conversion API.");
        }
        model.commitFinancialDatabase();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
