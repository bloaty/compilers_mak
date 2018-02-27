package solutions.bloaty.misc.wci.api.messages.types;

import solutions.bloaty.misc.wci.api.messages.Message;

import java.util.Arrays;
import java.util.Collections;

public class ParserSummaryMessage implements Message.Type.Info {

    private final static String TOTAL_LINES_OF_CODE_FIELD_NAME = "total lines of code read";
    private final static String TOTAL_NUMBER_OF_ERRORS_FIELD_NAME = "total number of errors";
    private final static String TOTAL_TIME_ELAPSED_FIELD_NAME = "total time elapsed";

    private ParserSummaryMessage() { /* prevent instantiation */ }

    public static EmptyBuilder builder() {
        return new Builder();
    }

    public interface EmptyBuilder {
        BuilderWithTotalLinesOfCode setTotalLinesOfCode(int linesOfCode);
    }

    public interface BuilderWithTotalLinesOfCode {
        BuilderWithTotalNumberOfErrors setTotalNumberOfErrors(int totalNumberOfErrors);
    }

    public interface BuilderWithTotalNumberOfErrors {
        CompletedBuilder setTotalTimeElapsed(long totalTimeElapsed);
    }

    public interface CompletedBuilder extends Message.Builder<ParserSummaryMessage> {
        default Message<ParserSummaryMessage>build() {
            return Message.Builder.super.build();
        }
    }

    public static final class Builder implements EmptyBuilder,
                                                 BuilderWithTotalLinesOfCode,
                                                 BuilderWithTotalNumberOfErrors,
                                                 CompletedBuilder {

        private Message.Field<Integer> totalLinesOfCodeField;
        private Message.Field<Integer> totalNumberOfErrorsField;
        private Message.Field<Long> totalTimeElapsedField;

        private Builder() { }

        public BuilderWithTotalLinesOfCode setTotalLinesOfCode(int linesOfCode) {
            this.totalLinesOfCodeField = Message.Field.of(TOTAL_LINES_OF_CODE_FIELD_NAME, linesOfCode);
            return this;
        }

        public BuilderWithTotalNumberOfErrors setTotalNumberOfErrors(int totalNumberOfErrors) {
            this.totalNumberOfErrorsField = Message.Field.of(TOTAL_NUMBER_OF_ERRORS_FIELD_NAME, totalNumberOfErrors);
            return this;
        }

        public CompletedBuilder setTotalTimeElapsed(long totalTimeElapsed) {
            this.totalTimeElapsedField = Message.Field.of(TOTAL_TIME_ELAPSED_FIELD_NAME, totalTimeElapsed);
            return this;
        }

        @Override
        public Class<ParserSummaryMessage> getMessageType() {
            return ParserSummaryMessage.class;
        }

        @Override
        public Message.Body getMessageBody() {
            return () -> Collections.unmodifiableList(Arrays.asList(totalLinesOfCodeField, totalNumberOfErrorsField, totalTimeElapsedField));
        }

    }

}
