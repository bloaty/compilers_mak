package solutions.bloaty.misc.wci.api.frontend.tokens;

public interface ParsedToken<T extends TokenType.Parsed> extends RawToken<T> {
    Object getValue();
}
