package course;

public record ResponseChunk(String text, MessageType messageType) {

    static ResponseChunk intermediate(String text) {
        return new ResponseChunk(text, MessageType.INTERMEDIATE);
    }

    static ResponseChunk close() {
        return new ResponseChunk(null, MessageType.CLOSE);
    }
}
