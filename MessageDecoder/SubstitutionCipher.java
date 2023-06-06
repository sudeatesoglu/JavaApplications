public class SubstitutionCipher implements MessageEncoder, MessageDecoder {
    private int shift;

    public SubstitutionCipher(int shift) {
        this.shift = shift;
    }

    public String encode(String plainText) {
        StringBuilder encodedText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char activeCh = plainText.charAt(i);
            char encodedCh = shiftCh(activeCh);
            encodedText.append(encodedCh);
        }
        return encodedText.toString();
    }

    public String decode(String cipherText) {
        StringBuilder decodedText = new StringBuilder();

        for (int j = 0; j < cipherText.length(); j++) {
            char activeCh = cipherText.charAt(j);
            char decodedCh = undoShiftCh(activeCh);
            decodedText.append(decodedCh);
        }
        return decodedText.toString();
    }

    private char shiftCh(char ch) {
        if (Character.isLetter(ch)) {
            if (Character.isUpperCase(ch)) {
                ch += shift;
                if (ch > 'Z') {
                    ch -= 26;
                }
            } else {
                ch += shift;
                if (ch > 'z') {
                    ch -= 26;
                }
            }
        }
        return ch;
    }

    private char undoShiftCh(char ch) {
        if (Character.isLetter(ch)) {
            if (Character.isUpperCase(ch)) {
                ch -= shift;
                if (ch < 'A') {
                    ch += 26;
                }
            } else {
                ch -= shift;
                if (ch < 'a') {
                    ch += 26;
                }
            }
        }
        return ch;
    }
}