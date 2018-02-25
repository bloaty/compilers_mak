package solutions.bloaty.misc.wci.api.messages.types;

import solutions.bloaty.misc.wci.api.messages.Message;

import java.util.Arrays;
import java.util.Collections;

public final class SourceLineMessage implements Message.Type.Debug {

    private static final String LINE_NUMBER_FIELD_NAME = "line number";
    private static final String LINE_FIELD_NAME = "line";

    private SourceLineMessage() { /* prevent instantiation */ }

    public static EmptyBuilder builder() {
        return new Builder();
    }

    public interface EmptyBuilder {
        BuilderWithLine setLine(String line);
    }
    public interface BuilderWithLine {
        Message.Builder<SourceLineMessage> setLineNumber(int lineNumber);
    }
    public interface CompletedBuilder extends Message.Builder<SourceLineMessage> {
        default Message<SourceLineMessage> build() {
            return Message.Builder.super.build();
        }
    }

    public static final class Builder implements Message.Builder<SourceLineMessage>,
                                                 EmptyBuilder,
                                                 BuilderWithLine,
                                                 CompletedBuilder {
        private Message.Field<String> lineField;
        private Message.Field<Integer> lineNumberField;

        private Builder() { }

        public BuilderWithLine setLine(String line) {
            this.lineField = Message.Field.of(LINE_FIELD_NAME, line);
            return this;
        }

        public CompletedBuilder setLineNumber(int lineNumber) {
            this.lineNumberField = Message.Field.of(LINE_NUMBER_FIELD_NAME, lineNumber);
            return this;
        }

        @Override
        public Class<SourceLineMessage> getMessageType() {
            return SourceLineMessage.class;
        }

        @Override
        public Message.Body getMessageBody() {
            return () -> Collections.unmodifiableList(Arrays.asList(lineField, lineNumberField));
        }
    }

}
