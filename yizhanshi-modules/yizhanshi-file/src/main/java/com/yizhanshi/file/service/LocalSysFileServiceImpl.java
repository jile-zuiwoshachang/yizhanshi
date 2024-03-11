package com.yizhanshi.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.yizhanshi.file.utils.FileUploadUtils;

/**
 * 本地文件存储
 * 服务器文件存储！
 * 使用@Primary指定使用哪一个ServiceImpl
 * 对于小型应用或初期项目，使用本地文件存储无需额外的硬件和软件投入，可以快速部署和测试。
 * 本地文件系统的访问速度通常比远程文件系统更快，因为它避免了网络传输的延迟。
 * 可扩展性差：当文件数量增加到一定程度时，本地文件系统的存储容量和访问性能可能成为瓶颈。
 * 高可用性和灾备难以保证：本地文件系统较难实现数据的冗余备份和故障转移，一旦服务器硬件故障，可能会导致数据丢失。
 * 文件共享和协作困难：在分布式系统或微服务架构中，本地文件存储难以满足跨服务或跨地域的文件共享需求。
 * @author hejiale
 */
@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService
{
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;
    
    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        String name = FileUploadUtils.upload(localFilePath, file);
        String url = domain + localFilePrefix + name;
        return url;
    }
}
