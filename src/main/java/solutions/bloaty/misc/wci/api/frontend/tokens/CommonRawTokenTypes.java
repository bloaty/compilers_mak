package solutions.bloaty.misc.wci.api.frontend.tokens;

public enum CommonRawTokenTypes implements TokenType.Raw {

    EOF(false, true, true),
    PARSEABLE(true, false, false);

    private boolean isSemantic;
    private boolean isIgnorable;
    private boolean isTypographic;

    CommonRawTokenTypes(boolean isSemantic, boolean isIgnorable, boolean isTypographic) {
        this.isSemantic = isSemantic;
        this.isIgnorable = isIgnorable;
        this.isTypographic = isTypographic;
    }

    @Override
    public boolean isSemantic() {
        return isSemantic;
    }

    @Override
    public boolean isIgnorable() {
        return isIgnorable;
    }

    @Override
    public boolean isTypographic() {
        return isTypographic;
    }
}
