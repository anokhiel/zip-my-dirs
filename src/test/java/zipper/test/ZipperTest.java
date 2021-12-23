package zipper.test;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import zipper.Model;
import zipper.Zipper;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ZipperTest {
    TemporaryFolder sourceFolder;
    Path file1;
    Path dirPath1;
    Path dirPath2;
    Path dirPath3;
    Path file2;
    Path targetPath;
    TemporaryFolder targetFolder;
    Model model;
    Zipper zipper;

    @BeforeEach
    void setUp() throws IOException {
        sourceFolder = new TemporaryFolder();
        sourceFolder.create();
        file1 = sourceFolder.newFile("input.txt").toPath();
        dirPath1 = sourceFolder.newFolder("subfolder1").toPath();
        dirPath2 = sourceFolder.newFolder("subfolder2").toPath();
        dirPath3 = sourceFolder.newFolder("subfolder2","subfolder4","subfolder3").toPath();
        file2 = sourceFolder.newFile("subfolder2/input1.txt").toPath();
        targetFolder = new TemporaryFolder();
        targetFolder.create();
        targetPath=targetFolder.newFolder("target").toPath();
        model = new Model(sourceFolder.toString(), targetFolder.toString());
        zipper = new Zipper(model);
    }
    @Test
    void testDoerFullFolder() throws Exception {
        zipper.doer(dirPath2, targetPath);
        assertTrue(zipper.hasFiles(targetPath));
    }
    @Test
    void testDoerEmptyFolder() throws Exception {
        zipper.doer(dirPath1, targetPath);
        assertFalse(zipper.hasFiles(targetPath));
    }

    @Test
    void testEmptyFolderPack() throws Exception{
        zipper.pack(dirPath1, targetPath.toString());
        assertFalse(zipper.hasFiles(targetPath));
    }
    @Test
    void testFullFolderPack() throws Exception{
        zipper.pack(dirPath2, targetPath.toString());
        assertTrue(zipper.hasFiles(targetPath));
    }

    @Test
    void testHasDirs() {
        assertTrue(zipper.hasDirs(dirPath2));
        assertFalse(zipper.hasDirs(dirPath3));
    }
}