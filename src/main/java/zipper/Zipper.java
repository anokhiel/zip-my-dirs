package zipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper implements Runnable {
    private Model model;
    private Logger log = Logger.getLogger(Zipper.class.getName());

    public Zipper(Model model) {
        this.model = model;
    }

    public void run() {
        try {
            UI.statusLabel.setText("Action in progress!");
            doer(model.getSource(), model.getTarget());
            UI.disableButton = false;
            UI.resetButton();
            UI.statusLabel.setText("");
            log.log(Level.INFO, "Completed successfully");
        } catch (Exception ex) {
            log.log(Level.INFO, "An error has happened");
            ex.printStackTrace();
        }
    }

    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    private void doer(Path source, Path resultDir) throws Exception {

        if (!Files.exists(resultDir)) {
            Files.createDirectory(resultDir);
            log.log(Level.INFO, "Directory has been created " + resultDir.toString());
        }
        pack(source, resultDir.toAbsolutePath().toString() + "\\" + source.getFileName() + ".zip");
        Files.find(source, 1, (p, bfa) -> Files.isDirectory(p)
        ).forEach(p -> {
            if (!p.getFileName().toString().equals(source.getFileName().toString())) {
                try {
                    if (hasDirs(p)) {

                        doer(p, Paths.get(resultDir.toAbsolutePath().toString() + "\\" + p.getFileName().toString() + "\\"));
                        log.log(Level.INFO, "Long directory");
                        log.log(Level.INFO, resultDir.toAbsolutePath().toString() + "\\" + p.getFileName().toString());

                    } else {
                        log.log(Level.INFO, resultDir.toAbsolutePath().toString() + "\\" + p.getFileName().toString());
                        log.log(Level.INFO, "Short directory");
                        pack(p, resultDir.toAbsolutePath().toString() + "\\" + p.getFileName() + ".zip");

                    }
                } catch (Exception e) {
                    log.log(Level.INFO, "An error while packing has happened");
                    e.printStackTrace();
                }
            }

        });
    }

    public void pack(Path sourceDirPath, String zipFilePath) throws IOException {
        Path zip = Paths.get(zipFilePath);
        if (Files.exists(zip)) {
            Files.delete(zip);
        }
        Path p = Files.createFile(zip);
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Files.find(sourceDirPath, 1, (p2, bfa2) -> !Files.isDirectory(p2))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            log.log(Level.INFO, "An error of archive");
                            System.err.println(e);
                        }
                    });
        }
    }

    public boolean hasDirs(Path p) {
        try {
            log.log(Level.INFO, "Depth is " + String.valueOf(Files.find(p, 1, (p1, bfa1) -> Files.isDirectory(p1)).toArray().length));
            return Files.find(p, 1, (p1, bfa1) -> Files.isDirectory(p1)).toArray().length > 1;
        } catch (IOException e) {
            return false;
        }
    }
}
