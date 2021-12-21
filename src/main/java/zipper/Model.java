package zipper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Model {
    private Path source;
    private Path target;

    public Model() {
    }

    public Model(String source, String target) {
        this.source = Paths.get(source);
        this.target = Paths.get(target);
    }

    public Model(Path source, Path target) {
        this.target = target;
        this.source = source;
    }

    public Path getSource() {
        return source;
    }

    public Path getTarget() {
        return target;
    }

    public void setSource(Path source) {
        this.source = source;
    }

    public void setTarget(Path target) {
        this.target = target;
    }
}
