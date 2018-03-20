package solutions.bloaty.misc.wci.api.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import solutions.bloaty.misc.wci.api.messages.Message;
import solutions.bloaty.misc.wci.api.messages.MessageHandler;
import solutions.bloaty.misc.wci.api.messages.MessageListener;
import solutions.bloaty.misc.wci.api.messages.MessageProducer;
import solutions.bloaty.misc.wci.api.messages.types.SourceLineMessage;

public abstract class Source implements AutoCloseable, MessageProducer {

    public static final char EOL = '\n';
    public static final char EOF = '\0';

    private final BufferedReader reader;
    private Line currentLine;
    private final MessageHandler messageHandler;

    public Source(BufferedReader reader, MessageHandler messageHandler) {
        this.currentLine = null;
        this.messageHandler = messageHandler;
        this.reader = reader;
    }

    public char currentChar() throws IOException {
        if (currentLine == null) {
            String line = reader.readLine();
            if (line == null) {
                return EOF;
            }
            currentLine = new Line(line, 1);
            sendMessage(SourceLineMessage.builder()
                    .setLine(line)
                    .setLineNumber(currentLine.lineNumber)
                    .build());
            return currentChar();
        }
        return currentLine.currentChar();
    }

    public char nextChar() throws IOException {

        char c = currentChar();

        if (c == Source.EOF) {
            return c;
        }

        if (c == Source.EOL) {
            String line = reader.readLine();
            if (line == null) {
                currentLine = null;
                return EOF;
            }
            currentLine = new Line(line, currentLine.lineNumber + 1);
            sendMessage(SourceLineMessage.builder()
                    .setLine(line)
                    .setLineNumber(currentLine.lineNumber)
                    .build());
            return c;
        }

        return currentLine.nextChar();
    }

    public char peekChar() throws IOException {

        char c = currentChar();

        if (c == Source.EOF) {
            return c;
        }

        return currentLine.peekChar();
    }

    public int lineNumber() {
        return currentLine.lineNumber;
    }

    public int currentPosition() {
        return currentLine.linePosition;
    }

    @Override
    public void close() throws IOException {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    @Override
    public boolean addMessageListener(MessageListener listener) {
        return messageHandler.addListener(listener);
    }

    @Override
    public boolean removeMessageListener(MessageListener listener) {
        return messageHandler.removeListener(listener);
    }

    @Override
    public <T extends Message.Type> boolean sendMessage(Message<T> message) {
        return messageHandler.sendMessage(message);
    }

    private static final class Line {

        private final char[] line;
        private final int lineLength;
        private final int lineNumber;
        private int linePosition;

        private Line(final String line, final int lineNumber) {
            this(line.toCharArray(), lineNumber);
        }

        private Line(final char[] line, final int lineNumber) {
            this.line = line;
            this.lineLength = line.length;
            this.lineNumber = lineNumber;
            this.linePosition = 0;
        }

        private char currentChar() {
            return (linePosition < lineLength) ? line[linePosition] : Source.EOL;
        }

        private char nextChar() {
            char c = currentChar();
            linePosition++;
            return c;
        }

        private char peekChar() {
            return (linePosition + 1 < lineLength) ? line[linePosition + 1] : Source.EOL;
        }

    }

    // TODO: remove me
    public static void main(String[] args) {
        String str = "aaa bbb\nccc ddd\n \neee fff\n\n\nggg\n\n\n";
        try (Source source = new Source(new BufferedReader(new StringReader(str)), null){}) {
            char c;
            while ((c = source.nextChar()) != Source.EOF) {
                System.out.print(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
