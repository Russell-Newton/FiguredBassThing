package Controllers;

public enum Notation {

    OTF ("\n5\n3", "\n\n\n", 0),
    TFO ("\n6\n3", "\n\n6", 1),
    FOT ("\n6\n4", "\n6\n4", 2),
    OTFS ("7\n5\n3", "\n\n7", 0),
    TFSO ("6\n5\n3", "\n6\n5", 1),
    FSOT ("6\n4\n3", "\n4\n3", 2),
    SOTF ("6\n4\n2", "\n4\n2", 3);


    private final String notation;
    private final String shorthand;
    private final int inversion;
    Notation(String notation, String shorthand, int inversion) {
        this.notation = notation;
        this.shorthand = shorthand;
        this.inversion = inversion;
    }

    public String getNotation() {
        return notation;
    }

    public String getShorthand() {
        return shorthand;
    }

    public int getInversion() {
        return inversion;
    }

    public boolean checkNotation(Notation notation) {
        return this.equals(notation);
    }

    public String toString() {
        return "notation =\n" + notation +
                "\nshorthand =\n" + shorthand +
                "\ninversion = " + inversion;
    }
}
