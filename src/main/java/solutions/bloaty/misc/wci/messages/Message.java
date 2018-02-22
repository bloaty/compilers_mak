package solutions.bloaty.misc.wci.messages;

public final class Message<T extends Message.Type> {

    private final Class<T> type;
    private final Body body;

    private Message(Class<T> type, Body body) {
        this.type = type;
        this.body = body;
    }

    @FunctionalInterface
    public interface Body {
        Iterable<Field<?>> getFields();
    }

    public static final class Field<T> {
        private final String name;
        private final T value;
        private Field(String name, T value) {
            this.name = name;
            this.value = value;
        }
        public static <T> Field of(String name, T value) {
            return new Field<>(name, value);
        }
        public String name() {
            return this.name;
        }
        public T value() {
            return this.value;
        }
    }

    public interface Type {
        public interface Fatal extends Type { }
        public interface Error extends Type { }
        public interface Warning extends Type { }
        public interface Info extends Type { }
        public interface Debug extends Type { }
        public interface Trace extends Type { }
    }

    public interface Builder<T extends Type> {
        default Message<T> build() {
            return new Message<>(getMessageType(), getMessageBody());
        };
        Class<T> getMessageType();
        Body getMessageBody();
    }

//    public enum BasicMessageType implements Type {
//
//        SOURCE_LINE("Source Line"),
//        SYNTAX_ERROR("Syntax Error"),
//        PARSER_SUMMARY("Parser Summary"),
//        INTERPRETER_SUMMARY("Interpreter Summary"),
//        COMPILER_SUMMARY("Compiler Summary"),
//        MISCELLANEOUS("Miscellaneous"),
//        TOKEN("Token"),
//        ASSIGN("Assign"),
//        FETCH("Fetch"),
//        BREAKPOINT("Breakpoint"),
//        RUNTIME_ERROR("Runtime Error"),
//        CALL("Call"),
//        RETURN("Return");
//
//        private final String name;
//
//        BasicMessageType(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public String getName() {
//            return name;
//        }
//
//    }

}
