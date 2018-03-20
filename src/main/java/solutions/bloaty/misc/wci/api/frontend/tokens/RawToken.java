package solutions.bloaty.misc.wci.api.frontend.tokens;

public interface RawToken<T extends TokenType.Raw> extends Token<T> {
    char[] getChars();
}
