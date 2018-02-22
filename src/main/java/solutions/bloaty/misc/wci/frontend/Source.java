package solutions.bloaty.misc.wci.frontend;

import solutions.bloaty.misc.wci.messages.*;
import solutions.bloaty.misc.wci.messages.types.SourceLineMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

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
                                         .setLineNumber(currentLine.lineNum)
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
            currentLine = new Line(line, currentLine.lineNum + 1);
            sendMessage(SourceLineMessage.builder()
                                         .setLine(line)
                                         .setLineNumber(currentLine.lineNum)
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

    public int getCurrentLineNumber() {
        return currentLine.lineNum;
    }

    public int getCurrentPosition() {
        return currentLine.linePos;
    }

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
    public boolean sendMessage(Message message) {
        return messageHandler.sendMessage(message);
    }

    private static final class Line {

        private final char[] line;
        private final int lineLen;
        private final int lineNum;
        private int linePos;

        private Line(final String line, final int lineNum) {
            this(line.toCharArray(), lineNum);
        }

        private Line(final char[] line, final int lineNum) {
            this.line = line;
            this.lineLen = line.length;
            this.lineNum = lineNum;
            this.linePos = 0;
        }

        private char currentChar() {
            return (linePos < lineLen) ? line[linePos] : Source.EOL;
        }

        private char nextChar() {
            char c = currentChar();
            linePos++;
            return c;
        }

        private char peekChar() {
            return (linePos + 1 < lineLen) ? line[linePos + 1] : Source.EOL;
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
