public class DocumentInfo {
    private String filename;
    private double readabilityScore;

    public DocumentInfo(String filename, double readabilityScore) {
        this.filename = filename;
        this.readabilityScore = readabilityScore;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getReadabilityScore() {
        return readabilityScore;
    }

    public void setReadabilityScore(int readabilityScore) {
        this.readabilityScore = readabilityScore;
    }

}
