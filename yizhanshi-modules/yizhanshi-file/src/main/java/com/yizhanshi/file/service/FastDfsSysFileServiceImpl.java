package com.yizhanshi.file.service;

import java.io.InputStream;
import com.alibaba.nacos.common.utils.IoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yizhanshi.common.core.utils.file.FileTypeUtils;

/**
 * FastDFS 文件存储
 * FastDFS 是为了解决海量数据存储而设计的，支持高并发和数据冗余，能够保证文件存取的高效率和数据的安全性。
 * 对于需要存储大量文件的应用，使用 FastDFS 可以有效地减少单点存储的成本，通过分布式的方式，可以利用更多低成本的硬件资源。
 * 相比本地文件系统，FastDFS 的部署和维护更为复杂，需要一定的运维知识。
 *  @author ruoyi
 */
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService
{
    /**
     * 域名或本机访问地址
     */
    @Value("${fdfs.domain}")
    public String domain;

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * FastDfs文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        InputStream inputStream = file.getInputStream();
        StorePath storePath = storageClient.uploadFile(inputStream, file.getSize(),
                FileTypeUtils.getExtension(file), null);
        IoUtils.closeQuietly(inputStream);
        return domain + "/" + storePath.getFullPath();
    }
}
