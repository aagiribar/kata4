package software.ulpgc.kata4;

public record Track (
        String title,
        String albumTitle,
        String genre,
        String composer,
        int milliseconds,
        int bytes,
        double unitPrice) {
}
