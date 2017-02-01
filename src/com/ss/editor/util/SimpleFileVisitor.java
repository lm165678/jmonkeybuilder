package com.ss.editor.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * The simple file visitor.
 *
 * @author JavaSaBr.
 */
public interface SimpleFileVisitor extends FileVisitor<Path> {

    @Override
    default FileVisitResult preVisitDirectory(@NotNull final Path dir, @NotNull final BasicFileAttributes attrs)
            throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    default FileVisitResult visitFile(@NotNull final Path file, @NotNull final BasicFileAttributes attrs)
            throws IOException {
        visit(file, attrs);
        return FileVisitResult.CONTINUE;
    }

    void visit(@NotNull final Path file, @NotNull final BasicFileAttributes attrs);

    @Override
    default FileVisitResult visitFileFailed(@NotNull final Path file, @NotNull final IOException exc)
            throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    default FileVisitResult postVisitDirectory(@NotNull final Path dir, @NotNull final IOException exc)
            throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
