package crelle.ftp.client;

import org.apache.commons.net.ftp.FTPFile;

import java.util.Collection;

/**
 * @author:crelle
 * @className:FtpClientService
 * @version:1.0.0
 * @date:2021/4/25
 * @description:XX
 **/
public interface FtpClientService {

    Collection<String> query();

    FTPFile[] queryByPath(String path);

    void upload(String fileName);

    void download(String fileName);
}
