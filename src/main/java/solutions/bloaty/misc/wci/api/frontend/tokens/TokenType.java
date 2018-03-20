package solutions.bloaty.misc.wci.api.frontend.tokens;

public interface TokenType {

    boolean isSemantic();
    boolean isIgnorable();
    boolean isTypographic();

    interface Raw extends TokenType {}
    interface Parsed extends Raw {}

}
