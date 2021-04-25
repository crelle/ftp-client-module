package crelle.ftp.client;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author:crelle
 * @className:FtpClientUtils
 * @version:1.0.0
 * @date:2021/4/25
 * @description:XX
 **/
public class FtpClient {

    private final String server;
    private final int port;
    private final String user;
    private final String password;
    private FTPClient ftp;

    FtpClient(String server, int port, String user, String password) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    void open() throws IOException {
        ftp = new FTPClient();

        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        ftp.login(user, password);
    }

    void close() throws IOException {
        ftp.disconnect();
    }

    Collection<String> listFiles(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);

        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    /**
     * @author:crelle
     * @date:2021/4/25
     * @title:putFileToPath
     * @description:上传文件到指定目录
     * @params:[file, path]
     * @return:void
     * @throw:
     */
    void putFileToPath(File file, String path) throws IOException {
        ftp.storeFile(path, new FileInputStream(file));
    }

    /**
     * @author:crelle
     * @date:2021/4/25
     * @title:batchutFileToPath
     * @description:批量上传文件到指定目录
     * @params:[files, path]
     * @return:void
     * @throw:
     */
    void batchPutFileToPath(File[] files, String path) throws IOException {
        for (File file : files) {
            ftp.storeFile(path, new FileInputStream(file));
        }
    }

    /**
     * @author:crelle
     * @date:2021/4/25
     * @title:deleteFile
     * @description:删除文件
     * @params:[filePath]
     * @return:boolean
     * @throw:
     */
    boolean deleteFile(String filePath) throws IOException {
        return ftp.deleteFile(filePath);
    }

    /**
     * @author:crelle
     * @date:2021/4/25
     * @title:deleteFile
     * @description:批量删除文件
     * @params:[filePath]
     * @return:boolean
     * @throw:
     */
    boolean batchDeleteFile(List<String> filePaths) throws IOException {
        filePaths.forEach(filePath -> {
            try {
                ftp.deleteFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    /**
     * @author:crelle
     * @date:2021/4/25
     * @title:downloadFile
     * @description:下载文件
     * @params:[source, destination]
     * @return:void
     * @throw:
     */
    void downloadFile(String source, String destination) throws IOException {
        FileOutputStream out = new FileOutputStream(destination);
        ftp.retrieveFile(source, out);
        out.close();
    }


}
