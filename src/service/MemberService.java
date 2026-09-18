package service;

import model.Member;
import exception.LibraryException;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final List<Member> members = new ArrayList<>();

    public void addMember(Member member) throws LibraryException {
        if (member.getName().isBlank()) {
            throw new LibraryException("Member name cannot be empty.");
        }
        if (findMember(member.getId()) != null) {
            throw new LibraryException("Member ID already exists.");
        }
        members.add(member);
    }

    public Member findMember(int id) {
        for (Member member : members) {
            if (member.getId() == id) return member;
        }
        return null;
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\nID | Member Name");
        System.out.println("-------------------------");
        for (Member member : members) System.out.println(member);
    }
}
