package model;

public class IssueRecord {
    private int bookId;
    private int memberId;

    public IssueRecord(int bookId, int memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }

    @Override
    public String toString() {
        return "Book ID: " + bookId + " | Member ID: " + memberId;
    }
}
