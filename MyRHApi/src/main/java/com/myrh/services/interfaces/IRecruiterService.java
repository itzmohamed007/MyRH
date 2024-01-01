package com.myrh.services.interfaces;

import com.myrh.controllers.interfaces.IFileGlobalController;
import com.myrh.dtos.requests.ReqRecruiter;
import com.myrh.dtos.responses.ResRecruiter;
import org.springframework.web.multipart.MultipartFile;

public interface IRecruiterService extends IGlobalService<ReqRecruiter, ResRecruiter, String> {

}
