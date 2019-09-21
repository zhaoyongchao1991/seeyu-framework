package com.seeyu.lang.utils;



import lombok.extern.slf4j.Slf4j;

import javax.security.auth.login.Configuration;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class IOUtils {

    public static int DEFAULT_BUFF_SIZE = 10485760;


    public static void copyBytes(InputStream in, OutputStream out, int buffSize, boolean close) throws IOException {
        try {
            copyBytes(in, out, buffSize);
            if (close) {
                out.close();
                out = null;
                in.close();
                in = null;
            }
        } finally {
            if (close) {
                closeStream(out);
                closeStream(in);
            }

        }

    }

    public static void copyBytes(InputStream in, OutputStream out, int buffSize) throws IOException {
        PrintStream ps = out instanceof PrintStream ? (PrintStream)out : null;
        byte[] buf = new byte[buffSize];

        for(int bytesRead = in.read(buf); bytesRead >= 0; bytesRead = in.read(buf)) {
            out.write(buf, 0, bytesRead);
            if (ps != null && ps.checkError()) {
                throw new IOException("Unable to write to output stream.");
            }
        }

    }

    public static void copyBytes(InputStream in, OutputStream out) throws IOException {
        copyBytes(in, out, 4096, true);
    }

    public static void copyBytes(InputStream in, OutputStream out, boolean close) throws IOException {
        copyBytes(in, out, 4096, close);
    }

    public static void copyBytes(InputStream in, OutputStream out, long count, boolean close) throws IOException {
        byte[] buf = new byte[4096];
        long bytesRemaining = count;

        try {
            while(bytesRemaining > 0L) {
                int bytesToRead = (int)(bytesRemaining < (long)buf.length ? bytesRemaining : (long)buf.length);
                int bytesRead = in.read(buf, 0, bytesToRead);
                if (bytesRead == -1) {
                    break;
                }

                out.write(buf, 0, bytesRead);
                bytesRemaining -= (long)bytesRead;
            }

            if (close) {
                out.close();
                out = null;
                in.close();
                in = null;
            }
        } finally {
            if (close) {
                closeStream(out);
                closeStream(in);
            }

        }

    }

    public static int wrappedReadForCompressedData(InputStream is, byte[] buf, int off, int len) throws IOException {
        try {
            return is.read(buf, off, len);
        } catch (IOException var5) {
            throw var5;
        } catch (Throwable var6) {
            throw new IOException("Error while reading compressed data", var6);
        }
    }

    public static void readFully(InputStream in, byte[] buf, int off, int len) throws IOException {
        int ret;
        for(int toRead = len; toRead > 0; off += ret) {
            ret = in.read(buf, off, toRead);
            if (ret < 0) {
                throw new IOException("Premature EOF from inputStream");
            }

            toRead -= ret;
        }

    }

    public static void skipFully(InputStream in, long len) throws IOException {
        long ret;
        for(long amt = len; amt > 0L; amt -= ret) {
            ret = in.skip(amt);
            if (ret == 0L) {
                int b = in.read();
                if (b == -1) {
                    throw new EOFException("Premature EOF from inputStream after skipping " + (len - amt) + " byte(s).");
                }

                ret = 1L;
            }
        }

    }

    public static void cleanup(Closeable... closeables) {
        Closeable[] var2 = closeables;
        int var3 = closeables.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Closeable c = var2[var4];
            if (c != null) {
                try {
                    c.close();
                } catch (IOException var7) {
                    if (log != null && log.isDebugEnabled()) {
                        log.debug("Exception in closing " + c, var7);
                    }
                }
            }
        }

    }

    public static void closeStream(Closeable stream) {
        cleanup(stream);
    }

    public static void closeSocket(Socket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    public static void writeFully(WritableByteChannel bc, ByteBuffer buf) throws IOException {
        do {
            bc.write(buf);
        } while(buf.remaining() > 0);

    }

    public static void writeFully(FileChannel fc, ByteBuffer buf, long offset) throws IOException {
        do {
            offset += (long)fc.write(buf, offset);
        } while(buf.remaining() > 0);

    }

    public static List<String> listDirectory(File dir, FilenameFilter filter) throws IOException {
        ArrayList list = new ArrayList();

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir.toPath());
            Throwable var4 = null;

            try {
                Iterator var5 = stream.iterator();

                while(true) {
                    String fileName;
                    do {
                        if (!var5.hasNext()) {
                            return list;
                        }

                        Path entry = (Path)var5.next();
                        fileName = entry.getFileName().toString();
                    } while(filter != null && !filter.accept(dir, fileName));

                    list.add(fileName);
                }
            } catch (Throwable var16) {
                var4 = var16;
                throw var16;
            } finally {
                if (stream != null) {
                    if (var4 != null) {
                        try {
                            stream.close();
                        } catch (Throwable var15) {
                            var4.addSuppressed(var15);
                        }
                    } else {
                        stream.close();
                    }
                }

            }
        } catch (DirectoryIteratorException var18) {
            throw var18.getCause();
        }
    }

    public static class NullOutputStream extends OutputStream {
        public NullOutputStream() {
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
        }
        @Override
        public void write(int b) throws IOException {
        }
    }
}

