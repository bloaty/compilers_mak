package solutions.bloaty.misc.wci.messages.types;

import solutions.bloaty.misc.wci.messages.Message;

import java.util.Arrays;
import java.util.Collections;

public final class SourceLineMessage implements Message.Type, Message.DebugType {

    private SourceLineMessage() {
        /* intentionally blank */
    }

    public interface EmptyBuilder {
        BuilderWithLine setLine(String line);
    }
    public interface BuilderWithLine {
        CompleteBuilder setLineNumber(int lineNumber);
    }
    public interface CompleteBuilder extends Message.Builder<SourceLineMessage> {
        default Message<SourceLineMessage> build() { return this.build(); }
    }

    public static final class Builder implements Message.Builder<SourceLineMessage>,
                                                 EmptyBuilder,
                                                 BuilderWithLine,
                                                 CompleteBuilder {
        private Message.Field<String> line;
        private Message.Field<Integer> lineNumber;

        private Builder() { /* intentionally blank */ }

        public static EmptyBuilder create() {
            return new Builder();
        }

        public BuilderWithLine setLine(String line) {
            this.line = Message.Field.of("line", line);
            return this;
        }

        public CompleteBuilder setLineNumber(int lineNumber) {
            this.lineNumber = Message.Field.of("lineNumber", lineNumber);
            return this;
        }

        @Override
        public Class<SourceLineMessage> getMessageType() {
            return SourceLineMessage.class;
        }

        @Override
        public Message.Body getMessageBody() {
            return () -> Collections.unmodifiableList(Arrays.asList(line, lineNumber));
        }
    }

}
