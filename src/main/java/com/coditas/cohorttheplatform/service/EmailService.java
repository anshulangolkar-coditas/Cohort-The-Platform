package com.coditas.cohorttheplatform.service;

import java.util.List;

public interface EmailService {

    String inviteUser(String emailId);
    void materialUploadEmail(List<String> emailList);
    void assignmentUpload(List<String> emailList);

}
