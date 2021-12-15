package ageevcode.myMessenger.domain;

public final class Views {
    public interface Id{}
    public interface IdName extends Id{}
    public interface FullMessage extends IdName{}

    public interface WithoutPassword{}
    public interface FullProfile extends WithoutPassword{}
}
