package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UploadService;
import com.itsol.recruit.utils.CommonConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.stream.Stream;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    private final Path root = Paths.get(CommonConst.DIRECTORY_UPLOAD_CV);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StatusJobRegisterRepository statusJobRegisterRepository;

    @Autowired
    JobRegisterRepository jobRegisterRepository;

    @Override
    public void init() {
        try {

            File directory = new File(String.valueOf(root));
            if(!directory.exists()){
                Files.createDirectory(root);
            }else{
                log.info("file is exits in backend");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(String username, MultipartFile file, Long jobId) {
        try {
            User user = userRepository.findOneByUserName(username);
            if(user == null){
                log.error("username: " + username + "is not exits");
                return;
            }
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            file.transferTo(new File("D:\\upFile\\"+fileName));
            // Save to job register
            registerJobForUser(user, fileName, jobId);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void saveAvatar(MultipartFile file, Long userId) {
        try {
            User user = userRepository.findOneById(userId);

            if(user == null){
                log.error("username: " + user.getName() + "is not exits");
                return;
            }
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            file.transferTo(new File("D:\\upFile\\"+fileName));
            // Save to avatar
            user.setAvatarName(fileName);
            userRepository.save(user);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void registerJobForUser(User user, String fileName, Long jobId){

        String url = CommonConst.URL_DOWNLOAD_CV + fileName;
        Job job = jobRepository.findOneById(jobId);
        JobRegister jobRegister = new JobRegister();
        jobRegister.setUser(user);
        jobRegister.setStatusJobRegister(statusJobRegisterRepository.findOneById(1L));
        jobRegister.setJob(job);
        jobRegister.setCv(url);
        jobRegister.setDateRegister(new Date(System.currentTimeMillis()));
        jobRegister.setIsDelete(false);
        jobRegisterRepository.save(jobRegister);
    }


    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
