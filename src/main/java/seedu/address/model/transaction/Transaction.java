package seedu.address.model.transaction;

import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * {@code Transaction} class encapsulates a transaction added to the financial database
 */
public class Transaction {

    private final Type type;
    private final Amount amount;
    private final Person person;
    private final Deadline deadline;

    private Interest interest;

    /**
     * Represents a transaction with non null fields {@code type}, {@code amount}, {@code deadline}
     * and {@code transaction}
     * @param type type of transaction, either a loan or a debt
     * @param amount the amount lent/owed by creditor/debtor respectively
     * @param deadline the date on which the payment is to be made
     * @param person the transactor loaning/borrowing the {@code amount}
     */
    public Transaction(Type type, Amount amount, Deadline deadline, Person person) {
        this.type = type;
        this.amount = amount;
        this.person = person;
        this.deadline = deadline;
    }


    public Type getType() {
        return type;
    }

    public Amount getAmount() {
        return amount;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Person getPerson() {
        return person;
    }

    public Interest getInterest() {
        return interest;
    }


    @Override
    public int hashCode() {
        return Objects.hash(type, amount, deadline, person);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) other;
        return other == this || (type.equals(transaction.type)
                && amount.equals(transaction.amount)
                && person.equals(transaction.person));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nPerson Details: ")
               .append(person.toString())
               .append("\nTransaction Details: ")
               .append(" Type: ")
               .append(type)
               .append(" Amount: ")
               .append(amount)
               .append(" Deadline: ")
               .append(deadline);
        if (interest != null) {
            builder.append(" Interest: ")
                   .append(interest);
        }
        return builder.toString();
    }
}
